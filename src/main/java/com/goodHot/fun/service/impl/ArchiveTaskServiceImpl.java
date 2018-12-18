package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.repository.ArchiveTaskRepository;
import com.goodHot.fun.service.ArchiveTaskService;
import com.goodHot.fun.service.PostService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ArchiveTaskServiceImpl implements ArchiveTaskService {

    @Autowired
    private ArchiveTaskRepository archiveTaskRepository;

    @Autowired
    private RetryTemplate retryTemplate;

    @Autowired
    private PostService postService;

    private static final ExecutorService EXECUTORS = Executors.newFixedThreadPool(6);

    @Override
    public Long taskCount() {
        return archiveTaskRepository.count();
    }

    @Override
    public Boolean start() {
        doTask();
        return null;
    }

    private void doTask() {
        ArchiveTask task = takeTask();
        if (task == null) {
            return;
        }
        task.setProcessing(true);
        archiveTaskRepository.save(task);
        EXECUTORS.submit(() -> {
            retryTemplate.execute(arg -> {
                task.setRetry(arg.getRetryCount());
                postService.processTask(task);
                archiveTaskRepository.save(task);
                return null;
            });
        });
        doTask();
    }

    private ArchiveTask takeTask() {
        Pageable pageable = new PageRequest(0, 1);
        Page<ArchiveTask> page = archiveTaskRepository.takeTask(pageable);
        List<ArchiveTask> content = page.getContent();
        return CollectionUtils.isEmpty(content) ? null : content.get(0);
    }
}
