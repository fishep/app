package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasswordDaoMybatisImplTest {

    @Autowired
    private UserMapper userMapper;

    private TokenDao tokenDao;

    private UserDO userDO;

    @BeforeEach
    public void setUp() {
        userDO = new UserDO();
        userDO.setId(1l);
        userDO.setName("test");
        userDO.setEmail("test@email.com");
        userDO.setPassword("hash12345678");
        userDO.setCreatedAt(Instant.now().toEpochMilli());
        userDO.setUpdatedAt(Instant.now().toEpochMilli());

        tokenDao = new PasswordDaoMybatisImpl();
        ReflectionTestUtils.setField(tokenDao, "userMapper", userMapper);
    }

    @Test
    void select() {
        assertNotNull(tokenDao.select(userDO));
    }
}