package com.connector.dto;

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
    private String description;
}
