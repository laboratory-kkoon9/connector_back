package com.connector.dto;

import com.connector.domain.Profile;
import com.connector.domain.Skill;
import com.connector.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProfileDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;


    // 빌더로 게터도 대신 할 수 있나?
    @Builder
    public ProfileDto(User user, String company, String location, String bio, List<String> skills) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
    }

//    public Profile toEntity() {
//        return Profile.builder()
//                .company(this.company)
//                .location(this.location)
//                .bio(this.location)
//                .skills()
//                .build();
//    }
}