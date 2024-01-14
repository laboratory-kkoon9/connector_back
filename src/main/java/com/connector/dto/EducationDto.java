package com.connector.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EducationDto {
    private String school;
    private Integer degree;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate endDate;
}
