package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

/**
 * 分类异常，包含同类型的所有异常  code 可能为分类的值  例如：1200 - 1299
 */
public class ValidateException extends AppException {
    public ValidateException() {
        super(StatusCode.VALIDATE_ERROR.getCode(), StatusCode.VALIDATE_ERROR.getMessage());
    }

    public ValidateException(String message) {
        super(message);
        this.code = StatusCode.VALIDATE_ERROR.getCode();
    }

    public ValidateException(Integer code, String message) {
        super(code, message);
    }
}
