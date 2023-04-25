package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class EntityNullException extends ServiceException {
    public EntityNullException() {
        super(StatusCode.ENTITY_NULL_ERROR.getCode(), StatusCode.ENTITY_NULL_ERROR.getMessage());
    }

    public EntityNullException(String message) {
        super(StatusCode.ENTITY_NULL_ERROR.getMessage() + ": " + message);
        this.code = StatusCode.ENTITY_NULL_ERROR.getCode();
    }
}
