package com.fishep.server.annotation;

import com.fishep.common.type.Guard;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fly.Fei
 * @Date 2023/5/18 17:36
 * @Desc 标记此注解，限定在特定的上下文执行 ，
 *     ERP, // 后台erp管理系统
 *     SHOP, // 前台商城系统
 *     APP;  // 第三方应用系统
 **/

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiGuard {
    Guard[] value() default {};
}
