package com.fishep.user.domain.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.user.domain.entity.User;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TokenRepositoryTest {

    @Autowired
    private TokenRepository tokenRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        user1 = new User(new UserId(1l), new UserName("test"), new Email("test@email.com"), Instant.now(), Instant.now());
        user2 = new User(new UserId(2l), new UserName("aaa"), new Email("aaa@email.com"), Instant.now(), Instant.now());
    }

    @Test
    void findPassword() {
        assertNotNull(tokenRepository.findPassword(user1));

        assertThrows(EntityNullException.class, () -> {
            tokenRepository.findPassword(user2);
        });
    }

    @Test
    void findCode() {
        assertNotNull(tokenRepository.findCode(user1));

        assertThrows(EntityNullException.class, () -> {
            tokenRepository.findCode(user2);
        });
    }
}