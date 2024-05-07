package com.connector.service;

import com.connector.domain.User;
import com.connector.dto.RegisterDto;
import com.connector.global.config.JpaAuditConfig;
import com.connector.global.config.QuerydslConfig;
import com.connector.global.token.TokenManager;
import com.connector.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({QuerydslConfig.class, JpaAuditConfig.class})
@ActiveProfiles("test")
@Sql("classpath:/sql/users.sql")
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Mock
    private TokenManager tokenManager;

    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(tokenManager, userRepository);
    }

    @DisplayName("dd")
    @Test
    void register_test1() {
        String email = "jjj123@naver.com";
        String name = "John";
        String password = "123123";
        RegisterDto registerDto = new RegisterDto(email, name, password);

        userService.register(registerDto);

        User user = userRepository.findById(1L).get();

        assertThat(user.getCreatedAt()).isNotNull();
    }
}

