package com.connector.dto;

import com.connector.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;

    @Builder
    public ProfileDto(User user, String company, String location, String bio, List<String> skills) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
    }
}
