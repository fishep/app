package com.fishep.user.infrastructure.dao.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserPlusMapper;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisPlusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PasswordDaoMybatisPlusImplTest {

    @Autowired
    private UserPlusMapper userPlusMapper;

    private TokenDao tokenDao;

    private UserDO userDO;

    @BeforeEach
    public void setUp() {
        userDO = new UserDO();
        userDO.setType(UserType.ADMIN.toString());

        tokenDao = new PasswordDaoMybatisPlusImpl();
        ReflectionTestUtils.setField(tokenDao, "userPlusMapper", userPlusMapper);
    }

    @Test
    void select1() {
        userDO.setId(1l);
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select2() {
        userDO.setName("root");
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select3() {
        userDO.setEmail("root@email.com");
        assertNotNull(tokenDao.select(userDO));
    }

    @Test
    void select4() {
        userDO.setPhoneNumber("16888888888");
        assertNotNull(tokenDao.select(userDO));
    }
}