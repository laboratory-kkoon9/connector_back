package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDetailDto {
    private UserDto user;
    private String company;
    private String location;
    private String status;
    private String bio;
    private String website;
    private List<String> skills;
    private List<ExperienceDto> experience;
    private List<EducationDto> education;

    public ProfileDetailDto(Profile profile) {
        user = UserDto.getUserDto(profile.getUser());
        company = profile.getCompany();
        location = profile.getLocation();
        status = profile.getStatus();
        bio = profile.getBio();
        website = profile.getWebsite();
        skills = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
        experience = profile.getExperiences().stream().map(ExperienceDto::getExperienceDto).collect(Collectors.toList());
        education = profile.getEducations().stream().map(EducationDto::getEducationDto).collect(Collectors.toList());
    }
}
