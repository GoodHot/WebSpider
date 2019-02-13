package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.domain.User;
import com.goodHot.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/user")
@RestController
public class UserAdminRest {

    @Autowired
    private UserService userService;

    @GetMapping("/page/{index}_{size}")
    public RestResult page(@PathVariable("index") Integer pageIndex, @PathVariable("size") Integer pageSize) {
        Page<User> page = userService.page(pageIndex, pageSize);
        return RestResult.ok(page);
    }

    @PostMapping("add-offical")
    public RestResult add(@RequestBody User user) {
        return RestResult.ok(userService.insertOffical(user));
    }

    @GetMapping("/staff-users")
    public RestResult allStaffUser() {
        return RestResult.ok(userService.findByStaff(User.STAFF_YES));
    }

}
