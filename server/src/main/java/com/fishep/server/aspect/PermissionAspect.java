package com.fishep.server.aspect;

import com.fishep.common.exception.PermissionException;
import com.fishep.server.annotation.Permission;
import com.fishep.server.annotation.Permissions;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
//@Profile("production")
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
        System.out.println("need Permissions: " + permissions.value());

        String[] ps = userPermissions();
        if (permissions.value().length > 0) {
            for (Permission p : permissions.value()) {
                if (!Arrays.asList(ps).contains(p.value())) {
                    throw new PermissionException(" Permission required, permission: " + p.value());
                }
            }
        }

        if (permissions.values().length > 0) {
            for (String p : permissions.values()) {
                if (!Arrays.asList(ps).contains(p)) {
                    throw new PermissionException(" Permission required, permission: " + p);
                }
            }
        }
    }

    private void check(Permission permission) {
        System.out.println("need Permissions: " + permission.value());

        String[] ps = userPermissions();
        if (!Arrays.asList(ps).contains(permission.value())) {
            throw new PermissionException(" Permission required, permission: " + permission.value());
        }
    }

    private String[] userPermissions() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println("App-User-Permissions: " + request.getHeader("App-User-Permissions"));

        String permissions = request.getHeader("App-User-Permissions");
        if (permissions == null || permissions.isEmpty()) {
            throw new PermissionException("permissions is not exist!");
        }

        return permissions.split(",");
    }
}
