package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Category;
import com.goodHot.fun.domain.Story;
import com.goodHot.fun.domain.Post;
import com.goodHot.fun.service.CategoryService;
import com.goodHot.fun.service.StoryService;
import com.goodHot.fun.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/index")
public class IndexWebRest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostService postService;

    @Autowired
    private StoryService collectService;

    @GetMapping("/{category}")
    public RestResult get(@PathVariable(required = false) String category) {
        List<Category> categories = categoryService.getAllByIsShow();
        Page<Post> pager = postService.page(category, 1, 20);
        Page<Story> collect = collectService.page(1, 20);
        return RestResult.ok().add("categories", categories).add("posts", pager).add("collects", collect);
    }

}
