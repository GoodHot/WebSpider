package com.goodHot.fun.rest.admin;


import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.enums.ArchiveEnum;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("archive")
@RestController
public class ArchiveRest {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/page/{index}_{size}")
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult page(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Archive> page = archiveService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

    @PostMapping("/approval/{id}")
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult approval(@PathVariable("id") String id, Integer status) {
        ArchiveEnum.Status statusEnum = ArchiveEnum.Status.find(status);
        ExceptionHelper.param(statusEnum == null, "不支持的审核状态：{}", status);
        return RestResult.ok(archiveService.approval(id, statusEnum));
    }

}
