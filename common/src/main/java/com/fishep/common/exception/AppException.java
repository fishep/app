package com.fishep.common.exception;

/**
 * 应用异常，包含所有自定义异常   code 可能为所有值    例如：100 - 无穷大
 * 分类异常，包含同类型的所有异常  code 可能为分类的值  例如：1200 - 1299
 * 具体异常，具体的异常          code 只能是一个值   例如：1201
 * <p>
 * 应用异常：AppException
 * 分类异常：ValidateException
 * 具体异常：EmailValidateException
 */
public class AppException extends RuntimeException {

    protected Integer code;

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Integer getCode() {
        return code;
    }
}
