package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class PermissionException extends ServiceException {
    public PermissionException() {
        super(StatusCode.PERMISSION_ERROR.getCode(), StatusCode.PERMISSION_ERROR.getMessage());
    }

    public PermissionException(String message) {
        super(StatusCode.PERMISSION_ERROR.getMessage() + ": " + message);
        this.code = StatusCode.PERMISSION_ERROR.getCode();
    }
}
