package com.connector.dto;
// 수업자료 가져옴
import com.connector.domain.Education;
import com.connector.domain.Experience;
import com.connector.domain.Skill;
import com.connector.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
public class ProfileDetailDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private String website;
    private List<String> skills;
    private List<GetExperienceDto> experience;
    private List<GetEducationDto> education;

    @Builder
    public ProfileDetailDto(User user, String company, String location, String bio, String website,
                            List<Skill> skills, List<Experience> experiences,
                            List<Education> educations) {
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.website = website;
        this.skills = skills.stream().map(Skill::getName).collect(Collectors.toList());
        this.experience = experiences.stream().map(GetExperienceDto::of).collect(Collectors.toList());
        this.education = educations.stream().map(GetEducationDto::of).collect(Collectors.toList());
    }
}
