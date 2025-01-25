package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.User;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.*;

import java.util.ArrayList;

@Getter
@Schema(description = "프로필 생성 및 수정 요청 DTO")
public class UpsertProfileDto {
    @Schema(description = "회사")
    private String company;
    @Schema(description = "개인 website")
    private String website;
    @Schema(description = "사는 지역")
    private String location;
    @Schema(description = "자기소개")
    private String bio;
    @Schema(description = "현재 구직 상태")
    private String status;
    @Schema(description = "기술 스택 comma(,)로 구분")
    private String skills;

    public Profile toEntity(User user) {
        return Profile.builder()
            .user(user)
            .company(company)
            .website(website)
            .bio(bio)
            .status(status)
            .skills(new ArrayList<>())
            .educations(new ArrayList<>())
            .experiences(new ArrayList<>())
            .build();
    }
}
