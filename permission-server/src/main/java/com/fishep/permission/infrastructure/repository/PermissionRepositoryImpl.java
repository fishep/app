package com.fishep.permission.infrastructure.repository;

import com.fishep.common.type.Guard;
import com.fishep.permission.domain.entity.Permission;
import com.fishep.permission.domain.repository.PermissionRepository;
import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author fly.fei
 * @Date 2023/7/6 16:15
 * @Desc
 **/
@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission[] find(UserType userType, UserId userId, Guard guard) {
        return new Permission[0];
    }
}
