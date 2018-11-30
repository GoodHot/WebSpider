package com.goodHot.fun.service;

import com.goodHot.fun.domain.Archive;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArchiveService {
    Page<Archive> page(Integer pageIndex, Integer pageSize);
    List<Archive> insertForTranslate(Archive archive);
    List<Archive> insertForTranslate(List<Archive> archive);
}
