package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Collect;
import com.goodHot.fun.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/collect")
public class CollectWebRest {

    @Autowired
    private CollectService collectService;

    @GetMapping("/{id}")
    public RestResult get(@PathVariable String id) {
        return RestResult.ok(collectService.get(id));
    }

    @GetMapping("/newest")
    public RestResult newest(){
        Page<Collect> pager = collectService.page(1, 20);
        return RestResult.ok(pager);
    }

}
