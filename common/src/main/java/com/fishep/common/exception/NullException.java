package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class NullException extends ServiceException {
    public NullException() {
        super(StatusCode.NULL_EXCEPTION.getCode(), Message.__(StatusCode.NULL_EXCEPTION.getMessage()));
    }

    public NullException(String message) {
        super(message);
        this.code = StatusCode.NULL_EXCEPTION.getCode();
    }
}
