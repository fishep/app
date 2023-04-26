package com.fishep.user.infrastructure.dao;

import com.fishep.user.infrastructure.data.UserDO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TokenDaoTest {

    @Autowired
    @Qualifier("CodeDaoRedisImpl")
    private TokenDao codeTokenDao;

    @Autowired
    @Qualifier("PasswordDaoMybatisImpl")
    private TokenDao passwordTokenDao;

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
    }

    @Test
    void select() {
        assertNotNull(passwordTokenDao.select(userDO));
        assertNotNull(codeTokenDao.select(userDO));
    }
}