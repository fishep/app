package com.fishep.user.annotation;

import com.fishep.user.validator.UserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface CheckUserName {
    String message() default "UserName format error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
