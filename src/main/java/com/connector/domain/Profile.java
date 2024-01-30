package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity                     // @Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 한다.
@Table(name = "profiles")   // DB(데이터베이스) 테이블 명 및 생성(만약 name을 정해주지 않으면 클래스 명으로 DB 테이블이 생성됨)
@NoArgsConstructor          // 기본 생성자 생성
@Getter                     // 필드의 Getter 자동 생성
public class Profile {
/*
    https://yoonbing9.tistory.com/19 참조
    @Id
        1. 직접 할당
        2. 엔티티의 기본키 필드에 값을 직접 넣어 등록

    @GeneratedValue
        1. 자동 할당
        2. 속성값이 4가지 있다.
            1) strategy = GenerationType.IDENTITY : 기본키 생성을 데이터베이스에 위험한다.                                       대표DBMS(MYSQL)
            2) strategy = GenerationType.SEQUENCE : 시퀀스 사용 및 @SequenceGenerator 의 어노테이션이 필요한다.(시퀀스 생성)       대표DBMS(ORACLE)
            3) strategy = GenerationType.TABLE : 키 생성용 테이블 사용, @TableGenerator 필요                                   대표DBMS(모든DBMS)
            4) strategy = GenerationType.AUTO : 데이터베이스 방언에 따라 자동 지정(기본값)
*/
    @Id                                                     // @Id는 직접할당
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // trategy = GenerationType.IDENTITY: DB에 기본키를 지정 및 생성 한다.
    @Column(name = "id", updatable = false)                 // updatable = false은 update 시점을 막는다.
    private Long id;

    @OneToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "company") // @Column은 DB 테이블의 컬럼명 및 컬럼 생성
    private String company;

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

    @Builder // 모든 필드의 매개변수를 생성자로 만들어 필요한 매개변수만 가져와 사용 할 수 있다.
    public Profile(Long id, User user, String company, String location, String bio, String website, List<Skill> skills,
                   List<Experience> experiences, List<Education> educations) {
        this.id = id;
        this.user = user;
        this.company = company;
        this.location = location;
        this.bio = bio;
        this.website = website;
        this.skills = skills;
        this.experiences = experiences;
        this.educations = educations;
    }

    public void addEducation(Education education) {
        this.educations.add(education);
        if (education.getProfile() != this) {
            education.setProfile(this);
        }
    }

    public void addExperience(Experience experience) {
        this.experiences.add(experience);
        if (experience.getProfile() != this) {
            experience.setProfile(this);
        }
    }


}
