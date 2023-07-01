package com.fishep.user.domain.entity;

import com.fishep.common.exception.NullException;
import com.fishep.user.type.Message;

public class Password extends Token {

    protected String value;

    protected String hash;

    private Password() {
    }

    public Password(String value) {
        if (value == null || value.isEmpty()) {
            throw new NullException(Message.__(Message.NULL_PASSWORD));
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
