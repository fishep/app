package com.fishep.common.exception;

import com.fishep.common.type.Message;
import com.fishep.common.type.StatusCode;

public class ServiceError extends AppException {
    public ServiceError() {
        super(StatusCode.SERVICE_ERROR.getCode(), Message.__(StatusCode.SERVICE_ERROR.getMessage()));
    }

    public ServiceError(Throwable cause) {
        super(cause);
        this.code = StatusCode.SERVICE_ERROR.getCode();
    }

    public ServiceError(String message) {
        super(message);
        this.code = StatusCode.SERVICE_ERROR.getCode();
    }

    public ServiceError(Integer code, String message) {
        super(code, message);
    }

    public ServiceError(String message, Throwable cause) {
        super(message, cause);
    }
}
