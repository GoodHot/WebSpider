package com.goodHot.fun.service.impl;

import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.repository.ArchiveRepository;
import com.goodHot.fun.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Override
    public Page<Archive> page(Integer pageIndex, Integer pageSize) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "created"));
        Page<Archive> page = archiveRepository.findAll(pageable);
        return page;
    }
}
