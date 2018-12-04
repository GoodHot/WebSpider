package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import org.springframework.web.bind.annotation.*;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/category")
@RestController
public class CategoryRest {

    @PostMapping
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult add() {
        return RestResult.ok();
    }

    @DeleteMapping
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult delete() {
        return RestResult.ok();
    }

    @PutMapping
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult update() {
        return RestResult.ok();
    }

    @GetMapping("/{id}")
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult get(@PathVariable("id") String id) {
        return RestResult.ok();
    }

    @GetMapping("/page/{index}_{size}")
    @CrossOrigin(WebsiteConfig.ADMIN_WEBSITE)
    public RestResult list(@PathVariable("index") Integer page, @PathVariable("size") Integer size) {
        return RestResult.ok();
    }

}
