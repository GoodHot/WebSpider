package com.goodHot.fun.rest.admin;

import com.goodHot.fun.conf.WebsiteConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/collect")
@RestController
@CrossOrigin(origins = WebsiteConfig.ADMIN_WEBSITE)
public class CollectRest {



}
