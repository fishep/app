package com.fishep.permission.client.service.impl;

import com.fishep.permission.client.service.PermissionService;
import org.springframework.stereotype.Component;

@Component
public class PermissionServiceFeignImpl implements PermissionService {
    @Override
    public String[] getUserPermissions(Long userId) {

//        @TODO
        String[] permissions = new String[1];
        permissions[0] = "user.test.api.permission.apiPermission";
        return permissions;
    }
}
