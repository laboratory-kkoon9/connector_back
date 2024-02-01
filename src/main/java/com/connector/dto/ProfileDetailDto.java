package com.connector.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProfileDetailDto {
    private UserDto user;
    private String bio;
    private String company;
    private String location;
    private List<String> skills;
    private List<ExperienceDto> experiences;
    private List<EducationDto> educations;
}
