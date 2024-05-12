package com.connector.service;

import com.connector.dto.RegisterDto;
import com.connector.global.exception.BadRequestException;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private TokenManager tokenManager;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(tokenManager, userRepository);
    }

    @Test
    @DisplayName("유효하지 않은 email에 대해서는 BadRequestException 예외를 던진다.")
    void register_test1() {
        // given
        String name = "name";
        String email = "email";
        String password = "123123";
        RegisterDto registerDto = new RegisterDto(name, email, password);

        // when then
        assertThatThrownBy(() -> userService.register(registerDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("email은 이메일 형식이어야 합니다.");

    }
}
