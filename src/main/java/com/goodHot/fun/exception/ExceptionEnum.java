package com.goodHot.fun.exception;

public enum ExceptionEnum {
    OK(200, "ok"),
    PARAM_ERROR(0x10000, "参数错误"),
    SYS_ERROR(0x20000, "系统错误"),
    ;


    ExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int code;
    public String message;
}
