package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.domain.ArchiveTask;
import com.goodHot.fun.domain.media.AbstractMedia;
import com.goodHot.fun.dto.req.ArchivePassReq;
import com.goodHot.fun.enums.ArchiveEnum;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.repository.ArchiveRepository;
import com.goodHot.fun.repository.ArchiveTaskRepository;
import com.goodHot.fun.service.ArchiveService;
import com.goodHot.fun.service.DownloadService;
import com.goodHot.fun.service.PostService;
import com.goodHot.fun.service.TranslateService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Autowired
    private ArchiveTaskRepository archiveTaskRepository;

    @Autowired
    private TranslateService translateService;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private PostService postService;

    @Override
    public Page<Archive> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Archive> page = archiveRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<Archive> insertForTranslate(Archive archive) {
        return insertForTranslate(Lists.newArrayList(archive));
    }

    @Override
    public List<Archive> insertForTranslate(List<Archive> archives) {
        archives.forEach(archive -> {
            String translate = translateService.translate(archive.getTitle());
            archive.setTranslateTitle(translate);
        });
        return archiveRepository.insert(archives);
    }

    @Override
    public Boolean approval(String id, ArchiveEnum.Status status) {
        ExceptionHelper.param(archiveRepository.existsById(id), "找不到数据：{}", id);
        Archive archive = archiveRepository.findById(id).get();
        if (status == ArchiveEnum.Status.REJECT) {
            archive.setStatus(ArchiveEnum.Status.REJECT.status);
            archiveRepository.save(archive);
            return true;
        }
        List<AbstractMedia> medias = archive.getMedias();
        downloadService.downloadMedias(medias);
        return null;
    }

    @Override
    public Boolean pass(ArchivePassReq archive) {
        Optional<Archive> opt = archiveRepository.findById(archive.getId());
        if (!opt.isPresent()) {
            return false;
        }
        Archive data = opt.get();
        data.setTranslateTitle(archive.getTitle());
        ArchiveTask task = new ArchiveTask();
        task.setArchive(data);
        task.setCategoryId(archive.getCategory());
        task.setProcessing(false);
        archiveTaskRepository.save(task);
        archiveRepository.deleteById(archive.getId());
        return StringUtils.isNotBlank(task.getId());
    }

    @Override
    public Boolean rejected(String id) {
        archiveRepository.deleteById(id);
        return true;
    }

}
