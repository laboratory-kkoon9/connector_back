package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDetailDto {
    @Schema(description = "유저 정보")
    private UserDto user;
    @Schema(description = "회사명", example = "SK Hynix")
    private String company;
    @Schema(description = "사는 곳", example = "Bucheon")
    private String location;
    @Schema(description = "현재 상태", example = "Developer")
    private String status;
    @Schema(description = "자기소개", example = "안녕하세요")
    private String bio;
    @Schema(description = "웹사이트", example = "https://www.naver.com")
    private String website;
    @Schema(description = "기술 스택")
    private List<String> skills;
    @Schema(description = "경력")
    private List<ExperienceDto> experience;
    @Schema(description = "학력")
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
