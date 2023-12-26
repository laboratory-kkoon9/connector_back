package com.connector.domain;

import com.connector.dto.UpsertProfileDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
@NoArgsConstructor
@Getter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @OneToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "company")
    private String company;

    @Column(name = "status")
    private String status;

    @Column(name = "location")
    private String location;

    @Column(name = "bio")
    private String bio;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Education> educations = new ArrayList<>();

    public void update(UpsertProfileDto profileDto) {
        if(profileDto.getStatus() != null) this.status = profileDto.getStatus();
        if(profileDto.getWebsite() != null) this.website = profileDto.getWebsite();
        if(profileDto.getLocation() != null) this.location = profileDto.getLocation();
        if(profileDto.getBio() != null) this.bio = profileDto.getBio();
        if(profileDto.getStatus() != null) this.status = profileDto.getStatus();
    }

    public void changeSkills(List<Skill> skills) {
        this.skills.clear();

        for(Skill skill : skills) {
            this.addSkill(skill);
        }
    }

    public void addSkill(Skill skill) {
        if (skill.getProfile() != this) {
            skill.setProfile(this);
        }

        this.skills.add(skill);
    }

    public void addExperience(Experience experience) {
        this.experiences.add(experience);
        if (experience.getProfile() != this) {
            experience.setProfile(this);
        }
    }

    public void addEducation(Education education) {
        this.educations.add(education);
        if (education.getProfile() != this) {
            education.setProfile(this);
        }
    }

    @Builder
    public Profile(Long id, User user, String company, String status, String location, String bio, String website, List<Skill> skills, List<Experience> experiences, List<Education> educations) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.status = status;
        this.location = location;
        this.bio = bio;
        this.website = website;
        this.skills = skills;
        this.experiences = experiences;
        this.educations = educations;
    }
}
