package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDetailDto {
    private UserDto user;
    private String status;
    private String company;
    private String website;
    private String location;
    private String bio;
    private List<String> skills;
    private List<ExperienceDto> experience;
    private List<EducationDto> education;

    public ProfileDetailDto(Profile profile) {
        user = UserDto.getUserDto(profile.getUser());
        status = profile.getStatus();
        company = profile.getCompany();
        website = profile.getWebsite();
        location = profile.getLocation();
        bio = profile.getBio();
        skills = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
        experience = profile.getExperiences().stream().map(ExperienceDto::getExperienceDto).collect(Collectors.toList());
        education = profile.getEducations().stream().map(EducationDto::getEducationDto).collect(Collectors.toList());
    }
}
