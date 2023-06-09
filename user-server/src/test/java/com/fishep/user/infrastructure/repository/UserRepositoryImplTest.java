package com.fishep.user.infrastructure.repository;

import com.fishep.common.exception.NullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.dao.impl.UserDaoMybatisImpl;
import com.fishep.user.infrastructure.mapper.UserMapper;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import com.fishep.user.type.UserType;
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
        assertNotNull(userRepository.find(UserType.ADMIN, new UserId(1l)));
        assertNotNull(userRepository.find(UserType.ADMIN, new UserName("test")));
        assertNotNull(userRepository.find(UserType.ADMIN, new Email("test@email.com")));
        assertNotNull(userRepository.find(UserType.ADMIN, new PhoneNumber("16888888888")));

        assertNull(userRepository.find(UserType.ADMIN, new UserId(2l)));
        assertNull(userRepository.find(UserType.ADMIN, new UserName("test.test")));
        assertNull(userRepository.find(UserType.ADMIN, new Email("test.test@email.com")));
        assertNull(userRepository.find(UserType.ADMIN, new PhoneNumber("17000000000")));
    }

    @Test
    void findOrException() {
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new UserId(1l));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new UserName("test"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new Email("test@email.com"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new PhoneNumber("16888888888"));
        });

        assertThrows(NullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new UserId(2l));
        });
        assertThrows(NullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new UserName("test.test"));
        });
        assertThrows(NullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new Email("test.test@email.com"));
        });
        assertThrows(NullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new PhoneNumber("17000000000"));
        });
    }

}