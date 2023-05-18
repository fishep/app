package com.fishep.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fly.Fei
 * @Date 2023/5/18 17:28
 * @Desc 标记此注解，限定在上下文为SHOP时执行，及请求只能从shop-gateway转发过来
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShopGuard {
}
