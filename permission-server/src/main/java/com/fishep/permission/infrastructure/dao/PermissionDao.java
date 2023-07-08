package com.fishep.permission.infrastructure.dao;

import com.fishep.permission.infrastructure.data.PermissionDO;

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

}
