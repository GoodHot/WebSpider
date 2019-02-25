package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Story;
import com.goodHot.fun.service.CategoryService;
import com.goodHot.fun.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/story")
public class StoryWebRest {

    @Autowired
    private StoryService collectService;

    @GetMapping("/{id}")
    public RestResult get(@PathVariable String id) {
        return RestResult.ok(collectService.get(id));
    }

    @GetMapping("/newest")
    public RestResult newest(){
        Page<Story> pager = collectService.page(1, 20);
        return RestResult.ok(pager);
    }

}
