package com.connector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동생성
public class ProfileDto {
    private UserDto user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
}