package com.fishep.common.type;


import com.fishep.common.exception.ValidateException;

public class PhoneNumber {
    public static String regex = "^[1-9]\\d{10}$";

    private String value;

    public PhoneNumber(String value) {
        if (!value.matches(regex)) {
            throw new ValidateException("PhoneNumber ValidateException: " + value);
        }

        this.value = value;
    }

    public Boolean equals(PhoneNumber phoneNumber) {
        return this.value.equals(phoneNumber.value);
    }

    public Boolean equals(String phoneNumber) {
        return this.value.equals(phoneNumber);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
