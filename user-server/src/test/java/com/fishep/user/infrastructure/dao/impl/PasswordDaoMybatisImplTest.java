package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.type.UserType;
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
        userDO.setType(UserType.ADMIN.toString());

        tokenDao = new PasswordDaoMybatisImpl();
        ReflectionTestUtils.setField(tokenDao, "userMapper", userMapper);
    }

    @Test
    void select1() {
        userDO.setId(1l);
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select2() {
        userDO.setName("test");
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select3() {
        userDO.setEmail("test@email.com");
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select4() {
        userDO.setPhoneNumber("16888888888");
        assertNotNull(tokenDao.select(userDO));
    }
}