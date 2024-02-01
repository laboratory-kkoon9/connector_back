package com.connector.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String avatar;

}