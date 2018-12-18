package com.goodHot.fun.service;

import com.goodHot.fun.dto.req.SpiderReq;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface SpiderService {
    Boolean startGag(SpiderReq req, ResponseBodyEmitter emitter);

    Boolean startCoub(SpiderReq req, ResponseBodyEmitter emitter);
}
