package com.goodHot.fun.util;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

public class Emitters {

    public static final void send(ResponseBodyEmitter emitter, String msg) {
        try {
            emitter.send(msg + "\r\n", MediaType.TEXT_HTML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
