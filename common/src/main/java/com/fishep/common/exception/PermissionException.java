package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class PermissionException extends ServiceException {
    public PermissionException() {
        super(StatusCode.PERMISSION_EXCEPTION.getCode(), StatusCode.PERMISSION_EXCEPTION.getMessage());
    }

    public PermissionException(String message) {
        super(StatusCode.PERMISSION_EXCEPTION.getMessage() + ": " + message);
        this.code = StatusCode.PERMISSION_EXCEPTION.getCode();
    }
}
