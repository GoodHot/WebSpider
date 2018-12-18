package com.goodHot.fun.service;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface ArchiveTaskService {
    Long taskCount();

    Boolean start(ResponseBodyEmitter emitter);
}
