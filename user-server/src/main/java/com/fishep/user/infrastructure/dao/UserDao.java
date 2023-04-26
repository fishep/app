package com.fishep.user.infrastructure.dao;

import com.fishep.user.infrastructure.data.UserDO;

public interface UserDao {

    Boolean insert(UserDO user);

    Boolean delete(UserDO user);

    Boolean update(UserDO user);

    Boolean updatePassword(UserDO user);

    UserDO select(UserDO user);

    Boolean isExist(UserDO user);
}
