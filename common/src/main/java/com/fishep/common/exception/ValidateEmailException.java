package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class ValidateEmailException extends ValidateException {
    public ValidateEmailException() {
        super(StatusCode.VALIDATE_EMAIL_EXCEPTION.getCode(), StatusCode.VALIDATE_EMAIL_EXCEPTION.getMessage());
    }

    public ValidateEmailException(String message) {
        super(StatusCode.VALIDATE_EMAIL_EXCEPTION.getMessage() + ": " + message);
        this.code = StatusCode.VALIDATE_EMAIL_EXCEPTION.getCode();
    }
}
