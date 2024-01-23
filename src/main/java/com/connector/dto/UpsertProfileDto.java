package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.User;
import lombok.Getter;

import java.util.Collections;

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
                .skills(Collections.emptyList())
                .educations(Collections.emptyList())
                .experiences(Collections.emptyList())
                .build();
    }
}
