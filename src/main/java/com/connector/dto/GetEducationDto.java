package com.connector.dto;

import com.connector.domain.Education;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetEducationDto {
    private Long id;
    private String school;
    private int degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;



    @Builder
    public GetEducationDto(Long id, String school, int degree, String fieldOfStudy
            ,LocalDate from, LocalDate to) {
            this.id = id;
            this.school = school;
            this.degree = degree;
            this.fieldOfStudy = fieldOfStudy;
            this.from = from;
            this.to = to;
    }

    // ??
    public static GetEducationDto of(Education education) {
        return GetEducationDto.builder()
                .id(education.getId())
                .school(education.getSchool())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .from(education.getStartDate())
                .to(education.getEndDate())
                .build();
    }
}
