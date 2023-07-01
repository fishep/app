package com.fishep.server.handler;

import com.fishep.common.exception.AppException;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.exception.ServiceWarn;
import com.fishep.common.type.Result;
import com.fishep.common.type.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    /**
     * 本应用只能抛 本应用的自定义异常，拦截异常，记录日志
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({AppException.class})
    public Result appException(AppException e) {
        if (e instanceof ServiceException) {
            this.logInfo(e);
        } else if (e instanceof ServiceWarn) {
            this.logWarn(e);
        } else {
            this.logError(e);
        }

        return new Result(e.getCode(), e.getMessage(), e.getStackTrace());
    }

    /**
     * 第三方依赖抛的异常，视情况 记录 warn 和 error
     * 开发应该查找原因，避免出现
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
    public Result argumentException(IllegalArgumentException e) {
        this.logWarn(e);
        return new Result<>(StatusCode.SERVICE_WARN, e.getMessage(), e.getStackTrace());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public Result sqlException(SQLIntegrityConstraintViolationException e) {
        this.logWarn(e);
        return new Result<>(StatusCode.SERVICE_WARN, e.getMessage(), e.getStackTrace());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BindException.class})
    public Result methodArgumentNotValidExceptionHandler(BindException e) {
        this.logWarn(e);
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new Result<>(StatusCode.SERVICE_WARN, e.getMessage(), objectError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NoSuchMessageException.class})
    public Result noSuchMessageException(NoSuchMessageException e) {
        this.logWarn(e);
        return new Result<>(StatusCode.SERVICE_WARN, e.getMessage(), e.getStackTrace());
    }

    /**
     * 异常信息记录，@TODO 可加入一些其他关键信息，上下文信息
     */
    public void logInfo(Exception e) {
        // 开发人员无须手动处理
        log.info(e.getMessage());
    }

    public void logWarn(Exception e) {
        // 开发人员应该跟进，在确定是否处理
        log.warn(e.getMessage());
    }

    public void logError(Exception e) {
        // 开发人员必须要手动处理
        log.error(e.getMessage());
    }
}
