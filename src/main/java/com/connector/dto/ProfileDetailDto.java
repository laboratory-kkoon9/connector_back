package com.connector.dto;

import com.connector.domain.Education;
import com.connector.domain.Experience;
import com.connector.domain.Skill;
import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDetailDto {
    @Schema(description = "유저 정보")
    private User user;
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
    private List<GetExperienceDto> experience;
    @Schema(description = "학력")
    private List<GetEducationDto> education;


    @Builder
    public ProfileDetailDto(User user, String company, String location, String status, String bio, String website, List<Skill> skills, List<Experience> experiences, List<Education> educations) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.status = status;
        this.bio = bio;
        this.website = website;
        this.skills = skills.stream().map(Skill::getName).collect(Collectors.toList());
        this.experience = experiences.stream().map(GetExperienceDto::of).collect(Collectors.toList());
        this.education = educations.stream().map(GetEducationDto::of).collect(Collectors.toList());
    }
}
