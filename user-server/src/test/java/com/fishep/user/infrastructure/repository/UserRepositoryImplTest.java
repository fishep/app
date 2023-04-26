package com.fishep.user.infrastructure.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.dao.impl.UserDaoMybatisImpl;
import com.fishep.user.infrastructure.mapper.UserMapper;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryImplTest {

    @Autowired
    private UserMapper userMapper;

    @Spy
    private UserDao userDao = new UserDaoMybatisImpl();

    @Spy
    private UserDOAssembler userDOAssembler = new UserDOAssembler();

    @InjectMocks
    private UserRepository userRepository = new UserRepositoryImpl();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(userDao, "userMapper", userMapper);
    }

    @Test
    void find() {
        assertNotNull(userRepository.find(new UserId(1l)));
        assertNotNull(userRepository.find(new UserName("test")));
        assertNotNull(userRepository.find(new Email("test@email.com")));

        assertNull(userRepository.find(new UserId(2l)));
        assertNull(userRepository.find(new UserName("test.test")));
        assertNull(userRepository.find(new Email("test.test@email.com")));

        assertNull(userRepository.find(new PhoneNumber("17000000000")));
    }

    @Test
    void findOrException() {
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new UserId(1l));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new UserName("test"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new Email("test@email.com"));
        });

        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new UserId(2l));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new UserName("test.test"));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new Email("test.test@email.com"));
        });

        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new PhoneNumber("17000000000"));
        });
    }

}