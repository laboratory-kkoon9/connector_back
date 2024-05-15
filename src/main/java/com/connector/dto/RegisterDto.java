package com.connector.dto;

import com.connector.domain.User;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class RegisterDto {
    private String name;
    @Email(message = "email은 이메일 형식이어야 합니다.")
    private String email;
    private String password;

    public RegisterDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
