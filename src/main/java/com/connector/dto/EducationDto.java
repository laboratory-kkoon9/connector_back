package com.connector.dto;

import com.connector.domain.Education;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "학력 추가 요청 DTO")
public class EducationDto {
    @Schema(description = "학교")
    private String school;
    @Schema(description = "학년")
    private Integer degree;
    @Schema(description = "전공")
    private String fieldOfStudy;
    @Schema(description = "학력 시간 날짜")
    private LocalDate from;
    @Schema(description = "학력 종료 날짜")
    private LocalDate to;
    @Schema(description = "현재 진행 여부")
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
