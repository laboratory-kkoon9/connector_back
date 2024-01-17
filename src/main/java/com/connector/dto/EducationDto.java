package com.connector.dto;

import com.connector.domain.Education;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EducationDto {
    private Long id;
    @Schema(description = "학교명", example = "가천대")
    private String school;
    @Schema(description = "학위", example = "3")
    private Integer degree;
    @Schema(description = "전공", example = "공과대")
    private String fieldOfStudy;
    @Schema(description = "시작일")
    private LocalDate from;
    @Schema(description = "종료일")
    private LocalDate to;
    public static EducationDto getEducationDto(Education education) {
        return new EducationDto(
                education.getId(),
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
