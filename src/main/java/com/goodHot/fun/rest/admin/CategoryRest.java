package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Category;
import com.goodHot.fun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/category")
@RestController
@CrossOrigin
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public RestResult add(@RequestBody Category category) {
        return RestResult.ok(categoryService.insert(category));
    }

    @DeleteMapping
    public RestResult delete() {
        return RestResult.ok();
    }

    @PutMapping
    public RestResult update() {
        return RestResult.ok();
    }

    @GetMapping("/{id}")
    public RestResult get(@PathVariable("id") String id) {
        return RestResult.ok();
    }

    @GetMapping("/page/{index}_{size}")
    public RestResult list(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Category> page = categoryService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

}
