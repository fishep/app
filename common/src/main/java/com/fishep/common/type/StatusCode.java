package com.fishep.common.type;

public enum StatusCode {
    /**
     * 100 - 999                  保持和http状态码含义一致
     * 1000 - Integer.MAX_VALUE   自定义状态码
     *   1000 - 1099 为一类异常，系统异常
     *   1100 - 1199 为一类异常, 数据格式验证异常
     *   1200 - 1299 为一类异常
     * ...
     */
    SUCCESS(200, "success"),
    FAILURE(400, "failure"),
    // ...
    SERVICE_ERROR(1000, "service_error"),
    RESPONSE_PACK_ERROR(1001, "response_pack_error"),
    CLASS_TYPE_ERROR(1002, "class_type_error"),
    ENTITY_NULL_ERROR(1003, "entity_null_error"),
    // ...
    VALIDATE_ERROR(1100, "validate_error"),
    VALIDATE_EMAIL_ERROR(1101, "validate_email_error");

    private Integer code;
    private String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
