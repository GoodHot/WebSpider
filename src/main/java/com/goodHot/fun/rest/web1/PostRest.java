package com.goodHot.fun.rest.web1;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容
 */
@RestController
@RequestMapping("post")
public class PostRest {

    /**
     * 热门
     * @return
     */
    @PostMapping("hot/{page}")
    public RestResult hot() {
        return RestResult.ok();
    }

    /**
     * 最新
     * @return
     */
    @PostMapping("fresh/{page}")
    public RestResult fresh() {
        return RestResult.ok();
    }

    /**
     * 详情
     * @return
     */
    @PostMapping("/detail/{id}")
    public RestResult get() {
        return RestResult.ok();
    }

}
