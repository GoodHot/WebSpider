package com.goodHot.fun.common;

import com.goodHot.fun.exception.ExceptionEnum;
import com.goodHot.fun.util.Times;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class RestResult {

    public static RestResult ok(Object obj) {
        return new RestResult(ExceptionEnum.OK, obj);
    }

    public static RestResult ok() {
        return ok(null);
    }

    public static RestResult err(ExceptionEnum ee, Object obj) {
        return new RestResult(ee, obj);
    }

    public static RestResult err(ExceptionEnum ee) {
        return err(ee, null);
    }

    public RestResult(ExceptionEnum ee) {
        this.code = ee.code;
        this.message = ee.message;
        this.timestamp = Times.now();
    }

    public RestResult(ExceptionEnum ee, Object data) {
        this(ee);
        this.data = data;
    }

    public RestResult ok(String key, Object value) {
        this.ok();
        this.add(key, value);
        return this;
    }

    public RestResult add(String key, Object value) {
        if (dataMap == null) {
            dataMap = new HashMap<>(5);
        }
        dataMap.put(key, value);
        return this;
    }

    private int code;
    private String message;
    private Date timestamp;
    private Object data;
    private Map<String, Object> dataMap;

}
