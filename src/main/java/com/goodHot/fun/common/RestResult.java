package com.goodHot.fun.common;

import com.goodHot.fun.exception.ExceptionEnum;
import com.goodHot.fun.util.Times;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class RestResult {

    public static RestResult ok(Object obj){
        return new RestResult(ExceptionEnum.OK, obj);
    }

    public static RestResult ok(){
        return ok(null);
    }

    public static RestResult err(ExceptionEnum ee, Object obj){
        return new RestResult(ee, obj);
    }

    public static RestResult err(ExceptionEnum ee){
        return err(ee, null);
    }

    public RestResult(ExceptionEnum ee){
        this.code = ee.code;
        this.message = ee.message;
        this.timestamp = Times.now();
    }

    public RestResult(ExceptionEnum ee, Object data){
        this(ee);
        this.data = data;
    }

    private int code;
    private String message;
    private Date timestamp;
    private Object data;

}
