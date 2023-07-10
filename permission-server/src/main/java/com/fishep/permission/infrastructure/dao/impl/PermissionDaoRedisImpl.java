package com.fishep.permission.infrastructure.dao.impl;

import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.permission.infrastructure.data.PermissionDO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author fly.fei
 * @Date 2023/7/10 17:30
 * @Desc
 **/
@Component
@Qualifier("PermissionDaoRedisImpl")
public class PermissionDaoRedisImpl implements PermissionDao {

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
        return null;
    }
}
