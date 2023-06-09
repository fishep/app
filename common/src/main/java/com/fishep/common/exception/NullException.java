package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class NullException extends ServiceException {
    public NullException() {
        super(StatusCode.NULL_EXCEPTION.getCode(), StatusCode.NULL_EXCEPTION.getMessage());
    }

    public NullException(String message) {
        super(StatusCode.NULL_EXCEPTION.getMessage() + ": " + message);
        this.code = StatusCode.NULL_EXCEPTION.getCode();
    }
}
