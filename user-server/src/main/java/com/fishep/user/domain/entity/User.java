package com.fishep.user.domain.entity;

import com.fishep.common.type.Email;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import lombok.Getter;

import java.time.Instant;

@Getter
public class User {

    private UserId id;

    private UserName name;

    private Email email;

    private Instant createdAt;

    private Instant updatedAt;

    public User(UserId id, UserName name, Email email, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private Token token;

    public void holdToken(Token token) {
        this.token = token;
    }

    public Token showToken() {
        return token;
    }
}
