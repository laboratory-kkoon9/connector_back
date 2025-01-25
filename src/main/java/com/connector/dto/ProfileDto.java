package com.connector.dto;

import com.connector.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "프로필 목록 조회 response DTO")
public class ProfileDto {
    @Schema(description = "유저 정보")
    private UserDto user;
    @Schema(description = "유저 회사")
    private String company;
    @Schema(description = "유저 지역 정보")
    private String location;
    @Schema(description = "유저 자기소개")
    private String bio;
    @Schema(description = "유저 기술 스택")
    private List<String> skills;

    @Builder
    public ProfileDto(UserDto user, String company, String location, String bio, List<String> skills) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
    }
}
