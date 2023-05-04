package com.fishep.permission.annotation;

import java.lang.annotation.*;

/**
 * 标记此注解，表示需要特定权限
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Permissions.class)
public @interface Permission {
    String value();
}
