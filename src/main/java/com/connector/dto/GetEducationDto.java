package com.connector.dto;

import com.connector.domain.Education;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "학력 조회 DTO")
public class GetEducationDto {
    @Schema(description = "학력 id")
    private Long id;
    @Schema(description = "학교")
    private String school;
    @Schema(description = "학년")
    private Integer degree;
    @Schema(description = "전공")
    private String fieldOfStudy;
    @Schema(description = "학력 시작 날짜")
    private LocalDate from;
    @Schema(description = "학력 종료 날짜")
    private LocalDate to;

    @Builder
    public GetEducationDto(Long id, String school, Integer degree, String fieldOfStudy, LocalDate from, LocalDate to) {
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.from = from;
        this.to = to;
    }

    public static GetEducationDto from(Education education) {
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
