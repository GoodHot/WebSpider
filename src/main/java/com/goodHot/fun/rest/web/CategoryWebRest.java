package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Category;
import com.goodHot.fun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(WebsiteConfig.WEB_PREFIX + "/category")
public class CategoryWebRest {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public RestResult all() {
        List<Category> categories = categoryService.getAllByIsShow();
        return RestResult.ok(categories);
    }

}
