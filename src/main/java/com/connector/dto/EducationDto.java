package com.connector.dto;

import com.connector.domain.Education;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EducationDto {
    private String school;
    private Integer degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;
    private Boolean current;

    public Education toEntity() {
        return Education.builder()
                .school(school)
                .degree(degree)
                .fieldOfStudy(fieldOfStudy)
                .startDate(from)
                .endDate(to)
                .build();
    }
}
