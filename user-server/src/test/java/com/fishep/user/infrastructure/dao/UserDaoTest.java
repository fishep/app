package com.fishep.user.infrastructure.dao;

import com.fishep.user.type.UserType;
import com.fishep.user.infrastructure.data.UserDO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    @Autowired
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