package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class ServiceException extends AppException {
    public ServiceException() {
        super(StatusCode.SERVICE_ERROR.getCode(), StatusCode.SERVICE_ERROR.getMessage());
    }

    public ServiceException(Throwable cause) {
        super(cause);
        this.code = StatusCode.SERVICE_ERROR.getCode();
    }

    public ServiceException(String message) {
        super(message);
        this.code = StatusCode.SERVICE_ERROR.getCode();
    }

    public ServiceException(Integer code, String message) {
        super(code, message);
    }
}
