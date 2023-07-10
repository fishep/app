package com.fishep.permission.infrastructure.repository;

import com.fishep.common.type.Guard;
import com.fishep.permission.domain.entity.Permission;
import com.fishep.permission.domain.repository.PermissionRepository;
import com.fishep.permission.infrastructure.assembler.PermissionDOAssembler;
import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.permission.infrastructure.data.PermissionDO;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author fly.fei
 * @Date 2023/7/6 16:15
 * @Desc
 **/
@Component
public class PermissionRepositoryImpl implements PermissionRepository {

    @Autowired
    @Qualifier("PermissionDaoRedisImpl")
    private PermissionDao redisDao;

    @Autowired
    @Qualifier("PermissionDaoMybatisPlusImpl")
    private PermissionDao dbDao;

    @Autowired
    private PermissionDOAssembler assembler;

    @Override
    public Permission[] findUserPermissions(UserType userType, UserId userId, Guard guard) {

        // @TODO 缓存

        List<PermissionDO> pdoList = dbDao.getUserPermission(userType.name(), userId.getValue(), guard.name());

        return assembler.toPermissionArray(pdoList);
    }
}
