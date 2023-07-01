package com.fishep.common.type;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @Author fly.fei
 * @Date 2023/6/27 11:23
 * @Desc
 **/
public class Message {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String SERVICE_EXCEPTION = "service.exception";
    public static final String NULL_EXCEPTION = "null.exception";
    public static final String TYPE_EXCEPTION = "type.exception";
    public static final String RESPONSE_EXCEPTION = "response.exception";
    public static final String AUTH_EXCEPTION = "auth.exception";
    public static final String PERMISSION_EXCEPTION = "permission.exception";
    public static final String VALIDATE_EXCEPTION = "validate.exception";
    public static final String VALIDATE_EMAIL_EXCEPTION = "validate.email.exception";
    public static final String SERVICE_WARN = "service.warn";
    public static final String SERVICE_ERROR = "service.error";

    public static final String REPEAT_SET_GUARD = "repeat.set.guard";
    public static final String REPEAT_SET_USER = "repeat.set.user";
    public static final String TOKEN_REPEAT = "token.repeat";
    public static final String TOKEN_NO = "token.no";
    public static final String PROHIBIT_HEADER_APP_GUARD = "prohibit.header.app.guard";
    public static final String PROHIBIT_HEADER_APP_USER_TYPE = "prohibit.header.app.user.type";
    public static final String PROHIBIT_HEADER_APP_USER_ID = "prohibit.header.app.user.id";
    public static final String PROHIBIT_HEADER_APP_USER_NAME = "prohibit.header.app.user.name";
    public static final String GUARD_CONTEXT_MISMATCH = "guard.context.mismatch";
    public static final String GUARD_CONTEXT_NULL = "guard.context.null";

    public static String __(String key) {
        return SpringUtil.getBean(MessageSource.class).getMessage(key, null, key, LocaleContextHolder.getLocale());
    }

    public static String __(String key, Object arg) {
        return SpringUtil.getBean(MessageSource.class).getMessage(key, new Object[]{arg}, key, LocaleContextHolder.getLocale());
    }

    public static String __(String key, Object[] args) {
        return SpringUtil.getBean(MessageSource.class).getMessage(key, args, key, LocaleContextHolder.getLocale());
    }
}
