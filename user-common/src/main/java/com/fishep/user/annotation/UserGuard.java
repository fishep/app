package com.fishep.user.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fly.Fei
 * @Date 2023/5/26 16:52
 * @Desc 只要登录即可，不管用户是  admin 还是 customer ，还是 supplier
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserGuard {
}
