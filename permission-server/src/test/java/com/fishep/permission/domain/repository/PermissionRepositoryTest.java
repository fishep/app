package com.fishep.permission.domain.repository;

import com.fishep.common.type.Guard;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @Author fly.fei
 * @Date 2023/7/10 16:41
 * @Desc
 **/
@SpringBootTest
class PermissionRepositoryTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    void findUserPermissions() {
        assertDoesNotThrow(() -> {
            permissionRepository.findUserPermissions(UserType.valueOf("ADMIN"), new UserId(1L), Guard.valueOf("ERP"));
        });
    }
}