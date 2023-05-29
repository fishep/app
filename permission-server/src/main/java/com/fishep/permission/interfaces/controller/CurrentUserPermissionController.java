package com.fishep.permission.interfaces.controller;

import com.fishep.permission.application.service.PermissionService;
import com.fishep.user.annotation.UserGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:34
 * @Desc 当前用户权限控制器
 **/
@UserGuard
@RestController
@RequestMapping("/current/user")
public class CurrentUserPermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/permissions")
    public String[] currentUserPermissions() {
        return permissionService.currentUserPermissions();
    }

}
