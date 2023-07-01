package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class PermissionException extends ServiceException {
    public PermissionException() {
        super(StatusCode.PERMISSION_EXCEPTION.getCode(), Message.__(StatusCode.PERMISSION_EXCEPTION.getMessage()));
    }

    public PermissionException(String message) {
        super(message);
        this.code = StatusCode.PERMISSION_EXCEPTION.getCode();
    }
}
