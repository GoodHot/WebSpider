package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.service.CategoryService;
import com.goodHot.fun.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/collect")
public class CollectWebRest {

    @Autowired
    private CollectService collectService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public RestResult get(@PathVariable String id) {
        return RestResult.ok().add("collect", collectService.get(id)).add("categories", categoryService.getAllByIsShow());
    }

}