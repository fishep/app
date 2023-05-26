package com.fishep.user.application.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.application.assembler.TokenDTOAssembler;
import com.fishep.user.application.assembler.UserDTOAssembler;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.domain.entity.Admin;
import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.domain.service.AuthService;
import com.fishep.user.domain.service.impl.AuthServiceImpl;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthCaseServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TokenRepository tokenRepository;

    @Spy
    AuthService authService = new AuthServiceImpl();

    @Spy
    UserDTOAssembler userDTOAssembler = new UserDTOAssembler();

    @Spy
    TokenDTOAssembler tokenDTOAssembler = new TokenDTOAssembler();

    @InjectMocks
    AuthCaseService authCaseService = new AuthCaseServiceImpl();

    @BeforeEach
    public void init() {
//        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(userDTOAssembler, "userRepository", userRepository);
        ReflectionTestUtils.setField(userDTOAssembler, "tokenRepository", tokenRepository);

        User user = new Admin(new UserId(1l), new UserName("fly"));
        user.setEmail(new Email("fly@test"));
        user.setPhoneNumber(new PhoneNumber("17000000000"));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        Password password = new Password("12345678");
        Code code = new Code("1234");

        when(userRepository.find(any(UserType.class), any(UserName.class))).thenReturn(user);
        when(userRepository.find(any(UserType.class), any(Email.class))).thenReturn(user);
        when(userRepository.find(any(UserType.class), any(PhoneNumber.class))).thenReturn(user);

        when(tokenRepository.findPassword(any())).thenReturn(password);
        when(tokenRepository.findCode(any())).thenReturn(code);
    }

    @Test
    void login() {
        // login by password
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("fly", "12345678")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("fly", "12345678888"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("fly@test", "12345678")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("fly@test", "12345678888"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("17000000000", "12345678")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("17000000000", "12345678888"));});

        // login by code
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("fly", "1234")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("fly", "4321"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("fly@test", "1234")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("fly@test", "4321"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("17000000000", "1234")));
        assertThrows(ServiceException.class, ()->{authCaseService.adminLoginErp(new LoginDTO("17000000000", "4321"));});
    }
}