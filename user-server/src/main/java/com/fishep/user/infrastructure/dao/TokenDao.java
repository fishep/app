package com.fishep.user.infrastructure.dao;

import com.fishep.user.infrastructure.data.TokenDO;
import com.fishep.user.infrastructure.data.UserDO;

public interface TokenDao {
     TokenDO select(UserDO user);
}
