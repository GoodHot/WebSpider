package com.goodHot.fun.exception;

public class ExceptionHelper {


    public static final void param(boolean flag, String message, Object... param) {
        if (flag) {
            if (param != null && param.length > 0) {
                throw new RestException(ExceptionEnum.PARAM_ERROR, String.format(message, param));
            } else {
                throw new RestException(ExceptionEnum.PARAM_ERROR, message);
            }
        }
    }

    public static final void param(boolean flag) {
        param(flag, null);
    }

}
