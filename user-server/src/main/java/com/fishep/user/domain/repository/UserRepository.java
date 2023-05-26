package com.fishep.user.domain.repository;

import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.entity.User;
import com.fishep.user.type.UserType;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;

public interface UserRepository {
    Boolean save(User user);

    User find(UserType type, UserId id);

    User findOrException(UserType type, UserId id);

    User find(UserType type, UserName name);

    User findOrException(UserType type, UserName name);

    User find(UserType type, Email email);

    User findOrException(UserType type, Email email);

    User find(UserType type, PhoneNumber phoneNumber);

    User findOrException(UserType type, PhoneNumber phoneNumber);
}
