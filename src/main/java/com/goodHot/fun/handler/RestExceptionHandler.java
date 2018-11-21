package com.goodHot.fun.handler;

import com.goodHot.fun.common.RestResult;
import com.goodHot.fun.exception.ExceptionEnum;
import com.goodHot.fun.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@RestController
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        String msg = e.getMessage();
        if(StringUtils.isEmpty(msg)){
            msg = "error !";
        }
        if(e instanceof RestException){
            RestException exp = (RestException) e;
            return RestResult.err(exp.getExceptionEnum(), exp.getMessage());
        }
        return RestResult.err(ExceptionEnum.SYS_ERROR, msg);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResult methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
        BindingResult rst = e.getBindingResult();
        List<FieldError> errs = rst.getFieldErrors();
        StringBuffer msg = new StringBuffer();
        for (FieldError err : errs) {
            msg.append(err.getField()).append("-->").append(err.getRejectedValue()).append("; ");
        }
        return RestResult.err(ExceptionEnum.PARAM_ERROR, msg.toString());
    }

}
