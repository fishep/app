package com.fishep.user.domain.entity;

import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class User {

    private UserId id;

    private UserName name;

    private Email email;

    private PhoneNumber phoneNumber;

    private Instant createdAt;

    private Instant updatedAt;

    private Token token;

    public User(UserId id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void holdToken(Token token) {
        this.token = token;
    }

    public Token showToken() {
        return token;
    }
}
