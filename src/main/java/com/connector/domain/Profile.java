package com.connector.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Profile {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
    private List<Experience> experience;
    private List<Education> education;

    @Builder
    public Profile(Long id, String name, String avatar, String company, String location, String bio, List<String> skills, List<Experience> experience, List<Education> education) {
        this.user = User.builder()
                .id(id)
                .name(name)
                .avatar(avatar)
                .build();
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
    }
}
