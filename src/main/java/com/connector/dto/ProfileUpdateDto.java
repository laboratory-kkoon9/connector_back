package com.connector.dto;

import com.connector.domain.*;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileUpdateDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private String status;
    private String website;
    private String skills;


    @Builder
    public ProfileUpdateDto(String company, String location, String bio, String status , String website,
                            String skills) {
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.status = status;
        this.website = website;
        this.skills = skills;
    }

    public Profile toEntity(User user) {
        return Profile.builder()
                .user(user)
                .company(company)
                .location(location)
                .bio(bio)
                .website(website)
                .status(status)
//                .skills(skills.)
                .build();
    }
}
