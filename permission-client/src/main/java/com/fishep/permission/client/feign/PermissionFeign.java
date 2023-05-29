package com.fishep.permission.client.feign;

import com.fishep.common.type.Result;
import com.fishep.permission.client.feign.impl.PermissionFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:45
 * @Desc
 **/
@FeignClient(value = "permission-server", fallback = PermissionFeignImpl.class)
public interface PermissionFeign {

    @GetMapping("/api/permission/current/user/permissions")
    Result<String[]> currentUserPermissions();

}
