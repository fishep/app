package com.fishep.permission.application.service.impl;

import com.fishep.permission.application.service.PermissionService;
import org.springframework.stereotype.Component;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:25
 * @Desc
 **/
@Component
public class PermissionServiceImpl implements PermissionService {
    @Override
    public String[] currentUserPermissions() {
        // @TODO 实现功能， 服务端缓存权限
        return new String[]{"permission.test.permission.check", "user.test.api.permission.apiPermission", "oms.order.admin.orders.create"};
    }
}
