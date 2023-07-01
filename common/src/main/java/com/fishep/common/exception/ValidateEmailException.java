package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class ValidateEmailException extends ValidateException {
    public ValidateEmailException() {
        super(StatusCode.VALIDATE_EMAIL_EXCEPTION.getCode(), Message.__(StatusCode.VALIDATE_EMAIL_EXCEPTION.getMessage()));
    }

    public ValidateEmailException(String message) {
        super(message);
        this.code = StatusCode.VALIDATE_EMAIL_EXCEPTION.getCode();
    }
}
