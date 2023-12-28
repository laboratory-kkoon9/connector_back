package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDto {
    private UserDto user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;

    public ProfileDto(Profile profile) {
        user = UserDto.getUserDto(profile.getUser());
        company = profile.getCompany();
        location = profile.getLocation();
        bio = profile.getBio();
        skills = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
    }
}
