package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentRest {

    /**
     * 评论
     *
     * @return
     */
    @PostMapping("/{id}")
    public RestResult comment() {
        return RestResult.ok();
    }

    /**
     * 回复
     *
     * @return
     */
    @PostMapping("/reply/{id}")
    public RestResult reply() {
        return RestResult.ok();
    }

    /**
     * 获取评论
     *
     * @return
     */
    @PostMapping("/page/{id}/{index}_{size}/{sort}")
    public RestResult list() {
        return RestResult.ok();
    }

    /**
     * 点赞
     *
     * @return
     */
    @PostMapping("like")
    public RestResult like() {
        return RestResult.ok();
    }

    /**
     * 踩
     *
     * @return
     */
    @PostMapping("dislike")
    public RestResult dislike() {
        return RestResult.ok();
    }

}
