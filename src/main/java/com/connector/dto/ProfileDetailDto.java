package com.connector.dto;

import com.connector.domain.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDetailDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
    private List<ExperienceDto> experience;
    private List<EducationDto> education;

    public ProfileDetailDto(Profile profile) {
        user = profile.getUser();
        company = profile.getExperiences().isEmpty() ? "" : profile.getExperiences().get(0).getCompany();
        location = profile.getLocation();
        bio = profile.getBio();
        skills = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
        experience = profile.getExperiences().stream().map(ExperienceDto::new).collect(Collectors.toList());
        education = profile.getEducations().stream().map(EducationDto::new).collect(Collectors.toList());
    }
}
