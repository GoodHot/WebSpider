package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.dto.req.SpiderReq;
import com.goodHot.fun.service.SpiderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/spider")
@RestController
@CrossOrigin(origins = WebsiteConfig.ADMIN_WEBSITE)
public class SpiderRest {

    private static final String SWAGGER_TAG = "爬虫";

    @Autowired
    private SpiderService spiderService;

    @ApiOperation(value = "9GAG爬虫", tags = SWAGGER_TAG)
    @PostMapping("gag")
    public RestResult gag(@ModelAttribute SpiderReq req) {
        return RestResult.ok(spiderService.startGag(req));
    }

    @ApiOperation(value = "COUB爬虫", tags = SWAGGER_TAG)
    @PostMapping("coub")
    public RestResult coub(@ModelAttribute SpiderReq req) {
        return RestResult.ok(spiderService.startCoub(req));
    }

}
