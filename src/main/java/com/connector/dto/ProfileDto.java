package com.connector.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProfileDto {
    private UserDto user;
    private String location;
    private String company;
    private String bio;
    private List<String> skills;
}