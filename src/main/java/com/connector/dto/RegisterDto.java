package com.connector.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterDto {
    private String name;
    private String email;
    private String password;
}
