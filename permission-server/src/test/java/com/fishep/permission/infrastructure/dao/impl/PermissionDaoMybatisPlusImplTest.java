package com.fishep.permission.infrastructure.dao.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.fishep.common.type.Guard;
import com.fishep.common.type.Locale;
import com.fishep.common.type.Module;
import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.permission.infrastructure.data.PermissionDO;
import com.fishep.permission.infrastructure.mapper.PermissionMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author fly.fei
 * @Date 2023/7/7 14:35
 * @Desc
 **/
@MybatisPlusTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PermissionDaoMybatisPlusImplTest {

    @Autowired
    private PermissionMapper permissionMapper;

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

        permissionDao = new PermissionDaoMybatisPlusImpl();
        ReflectionTestUtils.setField(permissionDao, "permissionMapper", permissionMapper);
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
}