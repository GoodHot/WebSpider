package com.goodHot.fun.exception;

public class RestException extends RuntimeException {

    private ExceptionEnum exceptionEnum;
    private String message;

    public RestException(ExceptionEnum ee){
        this.exceptionEnum = ee;
    }

    public RestException(ExceptionEnum ee, String message){
        this.exceptionEnum = ee;
        this.message = message;
    }

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
