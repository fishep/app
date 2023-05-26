package com.fishep.permission.aspect;

import com.fishep.common.context.UserContext;
import com.fishep.common.exception.PermissionException;
import com.fishep.permission.annotation.Permission;
import com.fishep.permission.annotation.Permissions;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PermissionAspect {

    @Before("@annotation(permission)")
    public void permission(Permission permission) {
        check(permission);
    }

    @Before("@within(permission)")
    public void withinPermission(Permission permission) {
        check(permission);
    }

    @Before("@annotation(permissions)")
    public void permissions(Permissions permissions) {
        check(permissions);
    }

    @Before("@within(permissions)")
    public void withinPermissions(Permissions permissions) {
        check(permissions);
    }

    private void check(Permissions permissions) {
        System.out.println("check Permissions: " + permissions.value());

        String[] ps = userPermissions();
        if (permissions.value().length > 0) {
            for (Permission p : permissions.value()) {
                if (!Arrays.asList(ps).contains(p.value())) {
                    throw new PermissionException("Permission required, permission: " + p.value());
                }
            }
        }

        if (permissions.values().length > 0) {
            for (String p : permissions.values()) {
                if (!Arrays.asList(ps).contains(p)) {
                    throw new PermissionException("Permission required, permission: " + p);
                }
            }
        }
    }

    private void check(Permission permission) {
        System.out.println("check Permissions: " + permission.value());

        String[] ps = userPermissions();
        if (!Arrays.asList(ps).contains(permission.value())) {
            throw new PermissionException("Permission required, permission: " + permission.value());
        }
    }

    private String[] userPermissions() {

        // @TODO 根据用户id，获取用户权限

        UserContext.User user = UserContext.getCurrentUser();

        return new String[]{"user.test.api.permission.apiPermission", "oms.order.admin.orders.create"};

//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        System.out.println("App-User-Permissions: " + request.getHeader("App-User-Permissions"));
//
//        String permissions = request.getHeader("App-User-Permissions");
//        if (permissions == null || permissions.isEmpty()) {
//            throw new PermissionException("permissions is not exist!");
//        }
//
//        return permissions.split(",");
    }
}
