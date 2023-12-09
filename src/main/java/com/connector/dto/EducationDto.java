package com.connector.dto;

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
}
