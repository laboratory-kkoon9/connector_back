package com.connector.dto;

import com.connector.domain.Education;
import com.connector.domain.Experience;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EducationDto {
    private Long id;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;
    private Boolean current;

    @Builder
    public EducationDto(Long id, String school, String degree, String fieldOfStudy, LocalDate from, LocalDate to, Boolean current) {
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.from = from;
        this.to = to;
        this.current = current;
    }
    public Education toEntity() {
        return Education.builder()
                .id(id)
                .school(school)
                .degree(degree)
                .fieldOfStudy(fieldOfStudy)
                .from(from)
                .to(to)
                .current(current)
                .build();
    }
}
