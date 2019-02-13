package com.goodHot.fun.rest.web1;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passport")
public class PassportRest {

    @PostMapping("signin")
    public RestResult signin() {
        return RestResult.ok();
    }

    @PostMapping("signup")
    public RestResult signup() {
        return RestResult.ok();
    }

}
