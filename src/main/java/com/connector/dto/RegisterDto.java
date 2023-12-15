package com.connector.dto;

import com.connector.domain.User;
import lombok.Getter;

@Getter
public class RegisterDto {
    private String name;
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}
