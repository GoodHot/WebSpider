package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.SpiderConfig;
import com.goodHot.fun.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("spider")
@RestController
public class SpiderRest {

    @Autowired
    private SpiderConfig config;

    @Autowired
    private SpiderService spiderService;

    @GetMapping("gag")
    public RestResult gag() {
        System.out.println(config.getGag().getUrl());
        return RestResult.ok();
    }


}
