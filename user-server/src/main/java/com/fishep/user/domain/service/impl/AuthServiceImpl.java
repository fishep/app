package com.fishep.user.domain.service.impl;

import com.fishep.common.exception.NullException;
import com.fishep.common.exception.TypeException;
import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.service.AuthService;
import com.fishep.user.type.Message;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {

    @Override
    public Boolean check(User user, Token token) {
        if (user.showToken() == null) {
            throw new NullException(Message.__(Message.NULL_TOKEN, user));
        }

        if (user.showToken().getClass() != token.getClass()) {
            throw new TypeException(Message.__(Message.TYPE_EXCEPTION, new Object[]{user.showToken().getClass().getName(), token.getClass().getName()}));
        }

        return user.showToken().equals(token);
    }
}
