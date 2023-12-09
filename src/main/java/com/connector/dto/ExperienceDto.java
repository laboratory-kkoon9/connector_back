package com.connector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExperienceDto {
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;
}
