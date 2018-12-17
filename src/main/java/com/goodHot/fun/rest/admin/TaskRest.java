package com.goodHot.fun.rest.admin;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.conf.WebsiteConfig;
import com.goodHot.fun.service.ArchiveTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(WebsiteConfig.ADMIN_PREFIX + "/task")
@RestController
public class TaskRest {

    @Autowired
    private ArchiveTaskService archiveTaskService;

    @GetMapping("/archive-task-count")
    public RestResult archiveTaskCount() {
        return RestResult.ok(archiveTaskService.taskCount());
    }

    @GetMapping("/start-archive-task")
    public RestResult startArchiveTask() {
        return RestResult.ok(archiveTaskService.start());
    }

}
