package com.fishep.user.infrastructure.dao;

import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeEach
    public void setUp() {
        userDO = new UserDO();
        userDO.setType(UserType.ADMIN.toString());

        String key1 = "CODE_" + UserType.ADMIN + "_1";
        String key2 = "CODE_" + UserType.CUSTOMER + "_1";

        redisTemplate.opsForValue().set(key1, "1234", 600, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key2, "1234", 600, TimeUnit.SECONDS);
    }

    @Test
    void select() {
        userDO.setId(1l);
        assertNotNull(codeTokenDao.select(userDO));
    }

    @Test
    void select1() {
        userDO.setId(1l);
        assertNotNull(passwordTokenDao.select(userDO));
    }

    @Test
    void select2() {
        userDO.setName("root");
        assertNotNull(passwordTokenDao.select(userDO));
    }

    @Test
    void select3() {
        userDO.setEmail("root@email.com");
        assertNotNull(passwordTokenDao.select(userDO));
    }

    @Test
    void select4() {
        userDO.setPhoneNumber("16888888888");
        assertNotNull(passwordTokenDao.select(userDO));
    }
}