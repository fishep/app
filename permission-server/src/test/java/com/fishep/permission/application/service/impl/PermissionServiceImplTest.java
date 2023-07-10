package com.fishep.permission.application.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.permission.application.dto.UserDTO;
import com.fishep.permission.application.service.PermissionService;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @Author fly.fei
 * @Date 2023/7/10 16:55
 * @Desc
 **/
@SpringBootTest
class PermissionServiceImplTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    void contextUserPermissions() {
        assertThrows(ServiceException.class, () -> {
            permissionService.contextUserPermissions();
        });
    }

    @Test
    void userPermissions() {
        assertDoesNotThrow(() -> {
            permissionService.userPermissions(new UserDTO(UserType.valueOf("ADMIN"), new UserId(1l), Guard.ERP));
        });
    }
}