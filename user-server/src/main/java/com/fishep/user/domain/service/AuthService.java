package com.fishep.user.domain.service;

import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;

public interface AuthService {
    Boolean check(User user, Token token);
}
