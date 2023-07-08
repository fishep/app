package com.fishep.permission.client.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
import com.fishep.permission.client.feign.PermissionFeign;
import com.fishep.permission.client.service.UserPermissionService;
import com.fishep.permission.type.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPermissionServiceFeignImpl implements UserPermissionService {

    @Autowired
    private PermissionFeign permissionFeign;

    @Override
    public String[] contextUserPermissions() {
        Result<String[]> result = permissionFeign.currentUserPermissions();
        if (result == null || result.getData() == null) {
            throw new ServiceException(Message.__(Message.CURRENT_USER_PERMISSION_IS_NULL));
        }

        return result.getData();
    }
}
