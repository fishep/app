package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class ClassTypeException extends ServiceException {
    public ClassTypeException() {
        super(StatusCode.CLASS_TYPE_ERROR.getCode(), StatusCode.CLASS_TYPE_ERROR.getMessage());
    }

    public ClassTypeException(String message) {
        super(StatusCode.CLASS_TYPE_ERROR.getMessage() + ": " + message);
        this.code = StatusCode.CLASS_TYPE_ERROR.getCode();
    }
}
