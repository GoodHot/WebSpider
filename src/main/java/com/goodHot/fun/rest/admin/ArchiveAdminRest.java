package com.goodHot.fun.rest.admin;


import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Archive;
import com.goodHot.fun.dto.req.ArchivePassReq;
import com.goodHot.fun.enums.ArchiveEnum;
import com.goodHot.fun.exception.ExceptionHelper;
import com.goodHot.fun.service.ArchiveService;
import com.goodHot.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/archive")
@RestController
public class ArchiveAdminRest {

    @Autowired
    private ArchiveService archiveService;

    /**
     * 列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{index}_{size}")
    public RestResult page(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Archive> page = archiveService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

    /**
     * 通过
     *
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/approval/{id}")
    public RestResult approval(@PathVariable("id") String id, Integer status) {
        ArchiveEnum.Status statusEnum = ArchiveEnum.Status.find(status);
        ExceptionHelper.param(statusEnum == null, "不支持的审核状态：{}", status);
        return RestResult.ok(archiveService.approval(id, statusEnum));
    }

    /**
     * 跳过，暂不处理
     *
     * @param archive
     * @return
     */
    @PostMapping("/pass")
    public RestResult pass(@RequestBody ArchivePassReq archive) {
        return RestResult.ok(archiveService.pass(archive));
    }

    /**
     * 未通过
     *
     * @param id
     * @return
     */
    @PostMapping("/rejected/{id}")
    public RestResult rejected(@PathVariable("id") String id) {
        return RestResult.ok(archiveService.rejected(id));
    }

}
