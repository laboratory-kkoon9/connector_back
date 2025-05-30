package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.LoginDto;
import com.connector.dto.RegisterDto;
import com.connector.dto.TokenResponseDto;
import com.connector.global.config.JpaAuditConfig;
import com.connector.global.config.QuerydslConfig;
import com.connector.global.exception.BadRequestException;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Import({QuerydslConfig.class, JpaAuditConfig.class})
@ActiveProfiles("test")
@Sql("classpath:/sql/users.sql")
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenManager tokenManager;

    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(tokenManager, userRepository, passwordEncoder);
    }

    @DisplayName("이미 있는 이메일로 회원가입 하게 되면 BadRequestException 예외를 던진다.")
    @Test
    void register_test1() {
        // given
        String email = "jjj123@naver.com";
        String name = "John";
        String password = "123123";
        RegisterDto registerDto = new RegisterDto(name, email, password);

        // when, then
        assertThatThrownBy(() -> userService.register(registerDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User already exists");
    }

    @DisplayName("회원가입 하게 되면 정보가 잘 저장된다.")
    @Test
    void register_test2() {
        // given
        String email = "jjj1234@naver.com";
        String name = "John";
        String password = "123123";
        RegisterDto registerDto = new RegisterDto(name, email, password);

        // when
        userService.register(registerDto);

        // then
        User result = userRepository.findByEmail(email).get();

        assertAll(
                () -> assertThat(result.getEmail()).isEqualTo(email),
                () -> assertThat(result.getName()).isEqualTo(name)
        );

    }

    @DisplayName("없는 이메일로 로그인을 하게 되면 BadRequestException 예외를 던진다.")
    @Test
    void login_test1() {
        // given
        String email = "jjj1234@naver.com";
        String password = "123123";
        LoginDto loginDto = new LoginDto(email, password);

        // when, then
        assertThatThrownBy(() -> userService.login(loginDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Invalid Credentials");
    }

    @DisplayName("비밀번호가 틀리면 BadRequestException 예외를 던진다.")
    @Test
    void login_test2() {
        // given
        String email = "jjj123@naver.com";
        String password = "1231234";
        LoginDto loginDto = new LoginDto(email, password);

        // when, then
        assertThatThrownBy(() -> userService.login(loginDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("비밀번호가 일치하지 않습니다.");
    }

    @DisplayName("로그인을 성공하면 토큰이 리턴된다.")
    @Test
    void login_test3() {
        // given
        String email = "jjj123@naver.com";
        String password = "123123";
        String token = "token";

        LoginDto loginDto = new LoginDto(email, password);
        given(tokenManager.generateToken(any())).willReturn(new TokenResponseDto(token));
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        // when
        TokenResponseDto result = userService.login(loginDto);

        // then
        assertThat(result.getToken()).isEqualTo(token);
    }
}
