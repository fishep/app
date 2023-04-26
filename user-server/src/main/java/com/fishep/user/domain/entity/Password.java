package com.fishep.user.domain.entity;

public class Password extends Token {

    protected String value;

    protected String hash;

    private Password() {
    }

    public Password(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty, value: " + value);
        }

        this.value = value;
        this.hash = "hash" + value; // @TODO hash 运算
    }

    public static Password hash(String hash) {
        Password password = new Password();
        password.value = null;
        password.hash = hash;

        return password;
    }

    @Override
    public String getCheckValue() {
        return hash;
    }
}
