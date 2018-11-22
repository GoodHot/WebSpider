package com.goodHot.fun.service;

import com.goodHot.fun.domain.Archive;
import org.springframework.data.domain.Page;

public interface ArchiveService {
    Page<Archive> page(Integer pageIndex, Integer pageSize);
}
