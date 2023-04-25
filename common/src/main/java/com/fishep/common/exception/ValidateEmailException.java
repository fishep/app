package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

/**
 * 具体异常，具体的异常          code 只能是一个值   例如：1201
 */
public class ValidateEmailException extends ValidateException {
    public ValidateEmailException() {
        super(StatusCode.VALIDATE_EMAIL_ERROR.getCode(), StatusCode.VALIDATE_EMAIL_ERROR.getMessage());
    }

    public ValidateEmailException(String message) {
        super(StatusCode.VALIDATE_EMAIL_ERROR.getMessage() + ": " + message);
        this.code = StatusCode.VALIDATE_EMAIL_ERROR.getCode();
    }
}
