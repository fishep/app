package com.fishep.user.validator;


import com.fishep.user.annotation.CheckUserName;
import com.fishep.user.type.UserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<CheckUserName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches(UserName.regex);
    }

    @Override
    public void initialize(CheckUserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
