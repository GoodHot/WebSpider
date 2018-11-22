package com.goodHot.fun.rest.admin;


import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("archive")
@RestController
public class ArchiveRest {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/page/{index}_{size}")
    public RestResult page(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Archive> page = archiveService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

}
