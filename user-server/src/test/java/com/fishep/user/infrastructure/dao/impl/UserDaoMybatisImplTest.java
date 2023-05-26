package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.type.UserType;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoMybatisImplTest {

    @Autowired
    private UserMapper userMapper;

    private UserDao userDao;

    private UserDO userDO;

    @BeforeEach
    public void setUp() {
        userDO = new UserDO();
        userDO.setType(UserType.ADMIN.toString());
        userDO.setId(2l);
        userDO.setName("zhangsan");
        userDO.setEmail("zhangsan@email.com");
        userDO.setPhoneNumber("16888888811");
        userDO.setPassword("********");
        userDO.setCreatedAt(Instant.now().toEpochMilli());
        userDO.setUpdatedAt(Instant.now().toEpochMilli());

        userDao = new UserDaoMybatisImpl();
        ReflectionTestUtils.setField(userDao, "userMapper", userMapper);
    }

    @Test
    @Order(1)
    void insert() {
        assertTrue(userDao.insert(userDO));
    }

    @Test
    @Order(6)
    void delete() {
        assertTrue(userDao.delete(userDO));
    }

    @Test
    @Order(2)
    void update() {
        userDO.setName("lisi");
        userDO.setEmail("lisi@email.com");
        assertTrue(userDao.update(userDO));
    }

    @Test
    @Order(3)
    void updatePassword() {
        userDO.setPassword("********************************");
        assertTrue(userDao.updatePassword(userDO));
    }

    @Test
    @Order(4)
    void select() {
        assertNotNull(userDao.select(userDO));
    }

    @Test
    @Order(5)
    void isExist() {
        assertTrue(userDao.isExist(userDO));
    }
}