package com.connector.dto;

import com.connector.domain.Education;
import com.connector.domain.Experience;
import com.connector.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileDetailDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
    private List<Experience> experience;
    private List<Education> education;

    @Builder
    public ProfileDetailDto(User user, String company, String location, String bio, List<String> skills, List<Experience> experience, List<Education> education) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
    }
}
