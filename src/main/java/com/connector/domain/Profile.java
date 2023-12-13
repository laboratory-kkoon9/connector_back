package com.connector.domain;

import com.connector.dto.UpsertProfileDto;
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

    @Column(name = "status")
    private String status;

    @Column(name = "location")
    private String location;

    @Column(name = "bio")
    private String bio;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL) // 참조를 당하는 쪽에서 읽기만 가능
    private List<Education> educations = new ArrayList<>();


    public void update(UpsertProfileDto profileDto) {
        if(profileDto.getLocation() != null) this.location = profileDto.getLocation();
        if(profileDto.getBio() != null) this.bio = profileDto.getBio();
        if(profileDto.getStatus() != null) this.status = profileDto.getStatus();
    }

    public void addSkills(List<Skill> skills) {
        for (Skill skill : skills) {
            this.addSkill(skill);
        }
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);

        //무한루프에 빠지지 않도록 체크
        if (skill.getProfile() != this) {
            skill.setProfile(this);
        }
    }
}
