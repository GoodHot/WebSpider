package com.goodHot.fun.rest.web;

import com.goodHot.fun.common.RestResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("upload")
public class UploadRest {


    /**
     * 上传头像
     *
     * @return
     */
    @PostMapping("avatar")
    public RestResult avatar() {
        return RestResult.ok();
    }

}
