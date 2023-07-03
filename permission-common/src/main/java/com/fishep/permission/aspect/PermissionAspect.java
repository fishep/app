package com.fishep.permission.aspect;

import com.fishep.common.context.UserContext;
import com.fishep.common.exception.PermissionException;
import com.fishep.permission.annotation.Permission;
import com.fishep.permission.annotation.Permissions;
import com.fishep.permission.api.PermissionProvider;
import com.fishep.permission.type.Message;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private PermissionProvider permissionProvider;

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
        String[] ps = currentUserPermissions();
        if (permissions.value().length > 0) {
            for (Permission p : permissions.value()) {
                if (!Arrays.asList(ps).contains(p.value())) {
                    throw new PermissionException(Message.__(Message.REQUIRED_PERMISSION, p.value()));
                }
            }
        }

        if (permissions.values().length > 0) {
            for (String p : permissions.values()) {
                if (!Arrays.asList(ps).contains(p)) {
                    throw new PermissionException(Message.__(Message.REQUIRED_PERMISSION, p));
                }
            }
        }
    }

    private void check(Permission permission) {
        String[] ps = currentUserPermissions();
        if (!Arrays.asList(ps).contains(permission.value())) {
            throw new PermissionException(Message.__(Message.REQUIRED_PERMISSION, permission.value()));
        }
    }

    private String[] currentUserPermissions() {
        UserContext.User user = UserContext.getCurrentUser();
        if (user == null) {
            throw new PermissionException(Message.__(Message.CURRENT_USER_DOES_NOT_EXIST));
        }

//        return new String[]{"user.test.api.permission.apiPermission", "oms.order.admin.orders.create"};
        return permissionProvider.currentUserPermissions();
    }
}
