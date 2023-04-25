package com.fishep.server.handler;


import com.fishep.common.exception.AppException;
import com.fishep.common.type.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({AppException.class})
    public Result appException(AppException e) {
        return new Result(e.getCode(), e.getMessage(), null);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
    public Result appException(IllegalArgumentException e) {
        return Result.failure(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class})
    public Result methodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return Result.failure(e.getMessage(), objectError);
    }

}
