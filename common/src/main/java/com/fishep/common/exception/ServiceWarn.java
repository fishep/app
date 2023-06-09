package com.fishep.common.exception;

import com.fishep.common.type.StatusCode;

public class ServiceWarn extends AppException {
    public ServiceWarn() {
        super(StatusCode.SERVICE_WARN.getCode(), StatusCode.SERVICE_WARN.getMessage());
    }

    public ServiceWarn(Throwable cause) {
        super(cause);
        this.code = StatusCode.SERVICE_WARN.getCode();
    }

    public ServiceWarn(String message) {
        super(message);
        this.code = StatusCode.SERVICE_WARN.getCode();
    }

    public ServiceWarn(Integer code, String message) {
        super(code, message);
    }
}
