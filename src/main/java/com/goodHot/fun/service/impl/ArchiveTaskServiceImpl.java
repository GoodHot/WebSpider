package com.goodHot.fun.service.impl;

import com.alibaba.fastjson.JSON;
import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.repository.ArchiveTaskRepository;
import com.goodHot.fun.service.ArchiveTaskService;
import com.goodHot.fun.service.PostService;
import com.goodHot.fun.util.Emitters;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
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
        return archiveTaskRepository.taskCount();
    }

    @Override
    public Boolean start(ResponseBodyEmitter emitter) {
        log.info("开始下载数据");
        Emitters.send(emitter, "start ............");
        doTask(emitter);
        return true;
    }

    private void doTask(ResponseBodyEmitter emitter) {
        ArchiveTask task = takeTask();
        if (task == null) {
            return;
        }
        task.setProcessing(true);
        archiveTaskRepository.save(task);
        EXECUTORS.submit(() -> {
            retryTemplate.execute(arg -> {
                log.info("开始同步数据：{}, 重试次数：{}", JSON.toJSONString(task), arg.getRetryCount());
                Emitters.send(emitter, "start -> " + task.getArchive().getSource() + ", retry -> " + arg.getRetryCount());
                task.setRetry(arg.getRetryCount());
                postService.processTask(task);
                archiveTaskRepository.save(task);
                return null;
            });
        });
        doTask(emitter);
    }

    private ArchiveTask takeTask() {
        Pageable pageable = new PageRequest(0, 1);
        Page<ArchiveTask> page = archiveTaskRepository.takeTask(pageable);
        List<ArchiveTask> content = page.getContent();
        return CollectionUtils.isEmpty(content) ? null : content.get(0);
    }
}
