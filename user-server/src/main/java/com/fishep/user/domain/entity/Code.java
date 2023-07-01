package com.fishep.user.domain.entity;

import com.fishep.common.exception.NullException;
import com.fishep.user.type.Message;

public class Code extends Token {
    protected String value;

    public Code(String value) {
        if (value == null || value.isEmpty()) {
            throw new NullException(Message.__(Message.NULL_CODE));
        }

        this.value = value;
    }

    @Override
    public String getCheckValue() {
        return value;
    }
}
