package com.goodHot.fun.admin;

import com.goodHot.fun.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("spider")
@RestController
public class SpiderRest {

    @Autowired
    private SpiderService spiderService;


}
