package com.fishep.permission.client.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.permission.client.feign.PermissionFeign;
import org.springframework.stereotype.Component;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:47
 * @Desc
 **/
@Component
public class PermissionFeignImpl implements PermissionFeign {
    @Override
    public Result<String[]> currentUserPermissions() {
        return Result.failure("service degradation, PermissionFeignImpl.currentUserPermissions()", null);
    }
}
