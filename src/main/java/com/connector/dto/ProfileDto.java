package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDto {
    @Schema(description = "유저 정보")
    private UserDto user;
    @Schema(description = "회사명", example = "SK Hynix")
    private String company;
    @Schema(description = "사는 곳", example = "Bucheon")
    private String location;
    @Schema(description = "현재 상태", example = "Developer")
    private String bio;
    @Schema(description = "기술 스택")
    private List<String> skills;

    public ProfileDto(Profile profile) {
        user = UserDto.getUserDto(profile.getUser());
        company = profile.getCompany();
        location = profile.getLocation();
        bio = profile.getBio();
        skills = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
    }
}
