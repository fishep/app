package com.fishep.permission.client.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
import com.fishep.permission.client.feign.PermissionFeign;
import com.fishep.permission.client.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionServiceFeignImpl implements PermissionService {

    @Autowired
    private PermissionFeign permissionFeign;

    @Override
    public String[] currentUserPermissions() {
        // @TODO 客户端缓存权限

        Result<String[]> result = permissionFeign.currentUserPermissions();
        if (result == null || result.getData() == null) {
            throw new ServiceException("PermissionServiceFeignImpl.currentUserPermissions() is null");
        }

        return result.getData();
    }
}
