package com.fishep.permission.infrastructure.dao.impl;

import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.permission.infrastructure.data.PermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author fly.fei
 * @Date 2023/7/10 17:30
 * @Desc
 **/
@Component
@Qualifier("PermissionDaoRedisImpl")
public class PermissionDaoRedisImpl implements PermissionDao {

    @Autowired
    private RedisTemplate redisTemplate;

    // @TODO

    @Override
    public Boolean insert(PermissionDO p) {
        return null;
    }

    @Override
    public Boolean delete(PermissionDO p) {
        return null;
    }

    @Override
    public Boolean update(PermissionDO p) {
        return null;
    }

    @Override
    public PermissionDO select(PermissionDO p) {
        return null;
    }

    @Override
    public List<PermissionDO> getUserPermission(String userType, Long userId, String guard) {
        String key = userType + "_" + userId + "_" + guard;
        List<PermissionDO> list = (List<PermissionDO>) redisTemplate.opsForValue().get(key);

        return list;
    }

    @Override
    public Boolean setUserPermission(String userType, Long userId, String guard, List<PermissionDO> permissionDOList) {
        String key = userType + "_" + userId + "_" + guard;
        redisTemplate.opsForValue().set(key, permissionDOList, 60, TimeUnit.MINUTES);

        return Boolean.TRUE;
    }
}
