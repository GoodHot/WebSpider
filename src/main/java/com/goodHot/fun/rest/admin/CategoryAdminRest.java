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
public class CategoryAdminRest {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类新增
     *
     * @param category
     * @return
     */
    @PostMapping
    public RestResult add(@RequestBody Category category) {
        return RestResult.ok(categoryService.insert(category));
    }

    /**
     * 删除
     *
     * @return
     */
    @DeleteMapping
    public RestResult delete() {
        // TODO: 2018/12/29 实现『分类』删除
        return RestResult.ok();
    }

    /**
     * 修改
     *
     * @return
     */
    @PutMapping
    public RestResult update() {
        // TODO: 2018/12/29 实现『分类』修改
        return RestResult.ok();
    }

    /**
     * 单个分类详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RestResult get(@PathVariable("id") String id) {
        // TODO: 2018/12/29 获取 单个『分类』详情
        return RestResult.ok();
    }

    /**
     * 列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{index}_{size}")
    public RestResult list(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Category> page = categoryService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

}
