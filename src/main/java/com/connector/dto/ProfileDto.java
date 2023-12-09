package com.connector.dto;

import com.connector.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProfileDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
    private List<ExperienceDto> experience;
    private List<EducationDto> education;

    //프로필 리스트 조회
    public ProfileDto(User user, String company, String location, String bio, List<String> skills) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
    }
}
