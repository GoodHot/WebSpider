package com.goodHot.fun.service;

import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.dto.req.ArchivePassReq;
import com.goodHot.fun.enums.ArchiveEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArchiveService {
    Page<Archive> page(Integer pageIndex, Integer pageSize);

    List<Archive> insertForTranslate(Archive archive);

    List<Archive> insertForTranslate(List<Archive> archive);

    /**
     * 审核
     *
     * @param id     资源ID
     * @param status com.goodHot.fun.enums.ArchiveEnum.Status
     * @return
     */
    Boolean approval(String id, ArchiveEnum.Status status);

    Boolean pass(ArchivePassReq archive);

    Boolean rejected(String id);
}
