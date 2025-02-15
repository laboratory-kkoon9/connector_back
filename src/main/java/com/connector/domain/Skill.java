package com.connector.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "skills")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "name")
    private String name;

    @Builder
    public Skill(Long id, Profile profile, String name) {
        this.id = id;
        this.profile = profile;
        this.name = name;
    }

    public void setProfile(Profile profile) {
        if (this.profile != null) {
            this.profile.getSkills().remove(this);
        }
        this.profile = profile;

        //무한루프에 빠지지 않도록 체크
        if(!profile.getSkills().contains(this)) {
            profile.getSkills().add(this);
        }
    }

    public static Skill of(String name) {
        return Skill.builder()
                .name(name)
                .build();
    }
}
