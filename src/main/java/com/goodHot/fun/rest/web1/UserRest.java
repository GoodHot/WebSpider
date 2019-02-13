package com.goodHot.fun.rest.web1;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRest {

    /**
     * 修改头像
     *
     * @return
     */
    @PostMapping("avatar")
    public RestResult avatar() {
        return RestResult.ok();
    }

    /**
     * 修改昵称
     *
     * @return
     */
    @PutMapping("nickname")
    public RestResult nickname() {
        return RestResult.ok();
    }

    /**
     * 获取用户
     *
     * @return
     */
    @PutMapping("/{get}")
    public RestResult get() {
        return RestResult.ok();
    }


}
