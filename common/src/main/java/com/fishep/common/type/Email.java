package com.fishep.common.type;


import com.fishep.common.exception.ValidateEmailException;

public class Email {

    //    public static String regex = "^[a-zA-Z]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
    public static String regex = "[\\w.]+@[\\w.]+";

    private String value;

    public Email(String value) {
        if (!value.matches(regex)) {
            throw new ValidateEmailException(value);
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
