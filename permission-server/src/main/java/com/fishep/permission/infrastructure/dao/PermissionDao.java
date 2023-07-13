package com.fishep.permission.infrastructure.dao;

import com.fishep.permission.infrastructure.data.PermissionDO;

import java.util.List;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:16
 * @Desc
 **/
public interface PermissionDao {

    Boolean insert(PermissionDO p);

    Boolean delete(PermissionDO p);

    Boolean update(PermissionDO p);

    PermissionDO select(PermissionDO p);

    List<PermissionDO> getUserPermission(String userType, Long userId, String guard);

    Boolean setUserPermission(String userType, Long userId, String guard, List<PermissionDO> permissionDOList);
}
