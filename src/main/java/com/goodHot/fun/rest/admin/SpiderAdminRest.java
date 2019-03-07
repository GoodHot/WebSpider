package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.dto.req.SpiderReq;
import com.goodHot.fun.service.SpiderResourceService;
import com.goodHot.fun.service.SpiderService;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@Slf4j
@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/spider")
@RestController
@CrossOrigin(origins = WebsiteConfig.ADMIN_WEBSITE)
public class SpiderAdminRest {

    private static final String SWAGGER_TAG = "爬虫";

    @Autowired
    private SpiderService spiderService;

    @Autowired
    private SpiderResourceService spiderResourceService;

    @ApiOperation(value = "9GAG爬虫", tags = SWAGGER_TAG)
    @GetMapping("gag/{size}")
    public ResponseBodyEmitter gag(@PathVariable Integer size) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        spiderService.startGag(new SpiderReq(size), emitter);
        return emitter;
    }

    @ApiOperation(value = "COUB爬虫", tags = SWAGGER_TAG)
    @GetMapping("coub/{size}")
    public ResponseBodyEmitter coub(@PathVariable Integer size) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        spiderService.startCoub(new SpiderReq(size), emitter);
        return emitter;
    }

    @GetMapping("resource")
    public RestResult resource(@RequestParam String url) throws UnirestException {
        return RestResult.ok(spiderResourceService.getResourceByParsevideo(url));
    }

}
