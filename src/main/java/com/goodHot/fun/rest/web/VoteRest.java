package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点赞
 */
@RestController
@RequestMapping("vote")
public class VoteRest {

    @PostMapping("like")
    public RestResult like() {
        return RestResult.ok();
    }

    @PostMapping("dislike")
    public RestResult dislike() {
        return RestResult.ok();
    }

    @PostMapping("cancel")
    public RestResult cancel(){
        return RestResult.ok();
    }

}
