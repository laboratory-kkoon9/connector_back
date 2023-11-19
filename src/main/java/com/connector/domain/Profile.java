package com.connector.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Profile {
    private User user;
    private String company;
    private String location;
    private List<String> skills;

    @Builder
    public Profile(Long id, String name, String avatar, String company, String location, List<String> skills) {
        this.user = new User(id, name, avatar);
        this.company = company;
        this.location = location;
        this.skills = skills;
    }
}
