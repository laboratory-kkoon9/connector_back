package com.connector.dto;

import com.connector.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterDto {
    private String name;
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
