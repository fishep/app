package com.fishep.user.domain.repository;

import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.entity.User;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;

public interface UserRepository {
    Boolean save(User user);

    User find(UserId id);

    User findOrException(UserId id);

    User find(UserName name);

    User findOrException(UserName name);

    User find(Email email);

    User findOrException(Email email);

    User find(PhoneNumber phoneNumber);

    User findOrException(PhoneNumber phoneNumber);
}
