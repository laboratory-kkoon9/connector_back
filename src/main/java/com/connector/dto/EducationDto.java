package com.connector.dto;

import com.connector.domain.Education;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EducationDto {
    private String school;
    private Integer degree;
    private String fieldOfStudy;
    private LocalDate from;
    private LocalDate to;

    public static EducationDto getEducationDto(Education education) {
        return new EducationDto(
                education.getSchool(),
                education.getDegree(),
                education.getFieldOfStudy(),
                education.getStartDate(),
                education.getEndDate());
    }

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
