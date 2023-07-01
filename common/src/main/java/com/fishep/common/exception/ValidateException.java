package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class ValidateException extends ServiceException {
    public ValidateException() {
        super(StatusCode.VALIDATE_EXCEPTION.getCode(), Message.__(StatusCode.VALIDATE_EXCEPTION.getMessage()));
    }

    public ValidateException(String message) {
        super(message);
        this.code = StatusCode.VALIDATE_EXCEPTION.getCode();
    }

    public ValidateException(Integer code, String message) {
        super(code, message);
    }
}
