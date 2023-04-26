package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.TokenDO;
import com.fishep.user.infrastructure.data.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CodeDaoRedisImpl")
public class CodeDaoRedisImpl implements TokenDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public TokenDO select(UserDO user) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        String value = (String) hashOperations.get("users_code", user.getId());
        if (value != null && !value.isEmpty()) {
            return new TokenDO(user.getId(), value);
        }

        return null;
    }
}
