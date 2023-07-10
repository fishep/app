package com.fishep.permission.infrastructure.dao;

import com.fishep.common.type.Guard;
import com.fishep.common.type.Locale;
import com.fishep.common.type.Module;
import com.fishep.permission.infrastructure.data.PermissionDO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author fly.fei
 * @Date 2023/7/10 14:53
 * @Desc
 **/
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PermissionDaoTest {

    @Autowired
    private PermissionDao permissionDao;

    private PermissionDO permissionDO;

    @BeforeEach
    void setUp() {
        permissionDO = new PermissionDO();
        permissionDO.setId(1000L);
        permissionDO.setName("test");
        permissionDO.setGuard(Guard.ERP.name());
        permissionDO.setModule(Module.PERMISSION.name());
        permissionDO.setLocale(new Locale("test", "测试权限"));
        permissionDO.setComment("this is a test");
        permissionDO.setCreatedAt(Instant.now().toEpochMilli());
    }

    @Test
    @Order(1)
    void insert() {
        assertTrue(permissionDao.insert(permissionDO));
    }

    @Test
    @Order(4)
    void delete() {
        assertTrue(permissionDao.delete(permissionDO));
    }

    @Test
    @Order(2)
    void update() {
        assertTrue(permissionDao.update(permissionDO));
    }

    @Test
    @Order(3)
    void select() {
        assertNotNull(permissionDao.select(permissionDO));
    }

    @Test
    @Order(5)
    void getUserPermission() {
        List<PermissionDO> list = permissionDao.getUserPermission("ADMIN", 1l, "ERP");

        assertNotNull(list);

        System.out.println("test");
    }

}