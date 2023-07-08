package com.fishep.permission.domain.repository;

import com.fishep.common.type.Guard;
import com.fishep.permission.domain.entity.Permission;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;

/**
 * @Author fly.fei
 * @Date 2023/7/6 15:48
 * @Desc
 **/
public interface PermissionRepository {
    Permission[] find(UserType userType, UserId userId, Guard guard);
}
