package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "educations")
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "school")
    private String school;

    @Column(name = "degree")
    private Integer degree;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Education(Long id, Profile profile, String school, Integer degree, String fieldOfStudy, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.profile = profile;
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public void setProfile(Profile profile) {
        if (this.profile != null) {
            this.profile.getExperiences().remove(this);
        }
        this.profile = profile;

        //무한루프에 빠지지 않도록 체크
        if (!profile.getEducations().contains(this)) {
            profile.getEducations().add(this);
        }
    }

}
