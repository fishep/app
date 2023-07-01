package com.fishep.common.type;

public enum StatusCode {
    /**
     * 100 - 999                  保持和http状态码含义一致
     *
     * 1000 - 1999   自定义状态码，此类为普通exception，开发人员无须手动处理
     *   1000 - 1099 为一类异常，服务异常，不分类的异常就放这里
     *   1100 - 1199 为一类异常, 数据格式验证异常
     *   1200 - 1999 预留
     *
     * 2000 - 2999   自定义状态码，此类为warn， 开发人员应该跟进，在确定是否处理
     *   2000 - 2099 为一类异常，服务异常，不分类的异常就放这里
     *   2100 - 2199 预留
     *
     * 3000 - 3999   自定义状态码，此类为error， 开发人员必须要手动处理
     *   3000 - 3099 为一类异常，服务异常，不分类的异常就放这里
     *   3100 - 3199 预留
     *
     * 4000 - Integer.MAX_VALUE  自定义状态码，预留
     *
     */
    //
    SUCCESS(200, Message.SUCCESS),
    FAILURE(400, Message.FAILURE),
    SERVICE_EXCEPTION(1000, Message.SERVICE_EXCEPTION),
    NULL_EXCEPTION(1001, Message.NULL_EXCEPTION),
    TYPE_EXCEPTION(1002, Message.TYPE_EXCEPTION),
    RESPONSE_EXCEPTION(1003, Message.RESPONSE_EXCEPTION),
    AUTH_EXCEPTION(1004, Message.AUTH_EXCEPTION),
    PERMISSION_EXCEPTION(1005, Message.PERMISSION_EXCEPTION),
    VALIDATE_EXCEPTION(1100, Message.VALIDATE_EXCEPTION),
    VALIDATE_EMAIL_EXCEPTION(1101, Message.VALIDATE_EMAIL_EXCEPTION),
    //
    SERVICE_WARN(2000, Message.SERVICE_WARN),
    //
    SERVICE_ERROR(3000, Message.SERVICE_ERROR);

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
