package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.dto.req.SpiderReq;
import com.goodHot.fun.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/spider")
@RestController
public class SpiderRest {

    @Autowired
    private SpiderService spiderService;

    @PostMapping("gag")
    public RestResult gag(@RequestBody SpiderReq req) {
        return RestResult.ok(spiderService.startGag(req));
    }

    @PostMapping("coub")
    public RestResult coub(@RequestBody SpiderReq req) {
        return RestResult.ok(spiderService.startCoub(req));
    }

}
