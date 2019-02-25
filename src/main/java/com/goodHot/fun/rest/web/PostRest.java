package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Post;
import com.goodHot.fun.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 内容
 */
@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/post")
public class PostRest {

    @Autowired
    private PostService postService;

    /**
     * 热门
     *
     * @return
     */
    @GetMapping("category/{category}")
    public RestResult category(@PathVariable("category") String category){
        Page<Post> pager = postService.page(category, 1, 20);
        return RestResult.ok(pager);
    }

    /**
     * 热门
     *
     * @return
     */
    @GetMapping("hot/{page}")
    public RestResult hot() {
        Page<Post> pager = postService.page("all", 1, 20);
        return RestResult.ok(pager);
    }

    /**
     * 最新
     *
     * @return
     */
    @PostMapping("fresh/{page}")
    public RestResult fresh() {
        return RestResult.ok();
    }

    /**
     * 详情
     *
     * @return
     */
    @PostMapping("/detail/{id}")
    public RestResult get() {
        return RestResult.ok();
    }

}
