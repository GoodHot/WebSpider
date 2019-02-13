package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.Post;
import com.goodHot.fun.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/post")
@RestController
public class PostAdminRest {

    @Autowired
    private PostService postService;

    /**
     * 管理员 列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page/{index}_{size}")
    public RestResult list(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<Post> page = postService.page("all", pageIndex, pageSize);
        return RestResult.ok(page);
    }

}
