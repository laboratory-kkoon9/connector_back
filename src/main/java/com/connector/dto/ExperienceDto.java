package com.connector.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ExperienceDto {
    private String company;
    private String position;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
