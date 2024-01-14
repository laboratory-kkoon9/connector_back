package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Education> educations = new ArrayList<>();

    @Builder
    public Profile(Long id, User user, String company, String location, String bio, List<Skill> skills, List<Experience> experiences, List<Education> educations) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.skills = skills;
        this.experiences = experiences;
        this.educations = educations;
    }
}
