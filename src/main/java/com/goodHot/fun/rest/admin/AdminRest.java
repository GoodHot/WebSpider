package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Admin;
import com.goodHot.fun.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/admin")
@RestController
public class AdminRest {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员 列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{index}_{size}")
    public RestResult list(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Admin> page = adminService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

    /**
     * 新增管理员
     *
     * @param admin
     * @return
     */
    @PostMapping
    public RestResult add(@RequestBody Admin admin) {
        return RestResult.ok(adminService.insert(admin));
    }

}
