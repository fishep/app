package com.fishep.user.infrastructure.dao.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserPlusMapper;
import com.fishep.user.type.UserType;
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
 * @Date 2023/7/5 14:44
 * @Desc
 **/
@MybatisPlusTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoMybatisPlusImplTest {
    
    @Autowired
    private UserPlusMapper userPlusMapper;

    private UserDao userDao;

    private UserDO userDO;

    @BeforeEach
    public void setUp() {
        userDO = new UserDO();
        userDO.setType(UserType.ADMIN.toString());
        userDO.setId(1000l);
        userDO.setName("zhangsan");
        userDO.setEmail("zhangsan@email.com");
        userDO.setPhoneNumber("16888888811");
        userDO.setPassword("********");
        userDO.setCreatedAt(Instant.now().toEpochMilli());
        userDO.setUpdatedAt(Instant.now().toEpochMilli());

        userDao = new UserDaoMybatisPlusImpl();
        ReflectionTestUtils.setField(userDao, "userPlusMapper", userPlusMapper);
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