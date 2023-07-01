package com.fishep.user.type;

import com.fishep.common.exception.ValidateException;

public class UserName {
    public static String regex = "^[a-z]+(\\.[a-z]+)*$";

    private String value;

    public UserName(String value) {
        if (!value.matches(regex)) {
            throw new ValidateException(Message.__(Message.VALIDATE_EXCEPTION, new Object[]{"UserName", value}));
        }
        this.value = value;
    }

    public Boolean equals(UserName userName) {
        return this.value.equals(userName.value);
    }

    public Boolean equals(String userName) {
        return this.value.equals(userName);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
