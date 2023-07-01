package com.fishep.common.type;


import com.fishep.common.exception.ValidateEmailException;
import com.fishep.common.exception.ValidateException;

public class Email {

    //    public static String regex = "^[a-zA-Z]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
    public static String regex = "[\\w.]+@[\\w.]+";

    private String value;

    public Email(String value) {
        if (!value.matches(regex)) {
            throw new ValidateException(Message.__(Message.VALIDATE_EXCEPTION, new Object[]{"Email", value}));
        }

        this.value = value;
    }

    public Boolean equals(Email email) {
        return this.value.equals(email.value);
    }

    public Boolean equals(String email) {
        return this.value.equals(email);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
