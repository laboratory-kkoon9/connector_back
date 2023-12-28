package com.connector.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "experiences")
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Experience(Long id, Profile profile, String company, String position, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.profile = profile;
        this.company = company;
        this.position = position;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setProfile(Profile profile) {
        if (this.profile != null) {
            this.profile.getExperiences().remove(this);
        }
        this.profile = profile;

        //무한루프에 빠지지 않도록 체크
        if(!profile.getExperiences().contains(this)) {
            profile.getExperiences().add(this);
        }
    }
}
