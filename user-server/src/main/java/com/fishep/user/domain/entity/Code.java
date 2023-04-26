package com.fishep.user.domain.entity;

public class Code extends Token {
    protected String value;

    public Code(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Code cannot be empty, value: " + value);
        }

        this.value = value;
    }

    @Override
    public String getCheckValue() {
        return value;
    }
}
