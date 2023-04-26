package com.fishep.user.domain.entity;

public abstract class Token {

    // 获取校验值
    public abstract String getCheckValue();

    public Boolean equals(Token token) {
        return this.getCheckValue().equals(token.getCheckValue());
    }
}
