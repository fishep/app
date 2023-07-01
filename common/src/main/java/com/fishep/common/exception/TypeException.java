package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class TypeException extends ServiceException {
    public TypeException() {
        super(StatusCode.TYPE_EXCEPTION.getCode(), Message.__(StatusCode.TYPE_EXCEPTION.getMessage()));
    }

    public TypeException(String message) {
        super(message);
        this.code = StatusCode.TYPE_EXCEPTION.getCode();
    }
}
