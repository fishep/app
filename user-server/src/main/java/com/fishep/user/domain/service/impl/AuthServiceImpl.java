package com.fishep.user.domain.service.impl;

import com.fishep.common.exception.ClassTypeException;
import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.service.AuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {

    @Override
    public Boolean check(User user, Token token) {
        if (user.showToken() == null) {
            throw new IllegalArgumentException("User token is null , user: " + user);
        }

        if (user.showToken().getClass() != token.getClass()) {
            throw new ClassTypeException(user.showToken().getClass().getName() + ", " + token.getClass().getName());
        }

        return user.showToken().equals(token);
    }
}
