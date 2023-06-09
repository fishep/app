package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class TypeException extends ServiceException {
    public TypeException() {
        super(StatusCode.TYPE_EXCEPTION.getCode(), StatusCode.TYPE_EXCEPTION.getMessage());
    }

    public TypeException(String message) {
        super(StatusCode.TYPE_EXCEPTION.getMessage() + ": " + message);
        this.code = StatusCode.TYPE_EXCEPTION.getCode();
    }
}
