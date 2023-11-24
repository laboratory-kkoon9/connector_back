package com.connector.dto;

import com.connector.domain.Education;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetEducationDto {
    private String school;
    private Integer degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;

    @Builder
    public GetEducationDto(String school, Integer degree, String fieldOfStudy, LocalDate from, LocalDate to) {
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.from = from;
        this.to = to;
    }

    public static GetEducationDto of(Education education) {
        return GetEducationDto.builder()
                .school(education.getSchool())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .from(education.getStartDate())
                .to(education.getEndDate())
                .build();
    }
}
