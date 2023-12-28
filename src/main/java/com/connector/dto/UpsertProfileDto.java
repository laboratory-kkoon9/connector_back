package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.User;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class UpsertProfileDto {
    private String company;
    private String website;
    private String location;
    private String bio;
    private String status;
    private String skills;

    public Profile toEntity(User user) {
        return Profile.builder()
                .user(user)
                .company(company)
                .website(website)
                .bio(bio)
                .status(status)
                .skills(new ArrayList<>())
                .experiences(new ArrayList<>())
                .educations(new ArrayList<>())
                .build();
    }
}