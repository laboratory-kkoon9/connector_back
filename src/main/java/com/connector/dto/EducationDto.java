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

    public EducationDto(Education education) {
        school = education.getSchool();
        degree = education.getDegree();
        fieldOfStudy = education.getFieldOfStudy();
        from = education.getStartDate();
        to = education.getEndDate();
    }
}
