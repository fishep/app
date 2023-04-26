package com.fishep.user.domain.repository;

import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.User;

public interface TokenRepository {
    Password findPassword(User user);

    Code findCode(User user);
}
