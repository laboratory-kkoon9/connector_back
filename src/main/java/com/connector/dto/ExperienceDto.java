package com.connector.dto;

import com.connector.domain.Experience;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExperienceDto {
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;

    public ExperienceDto(Experience experience) {
        company = experience.getCompany();
        position = experience.getPosition();
        description = experience.getDescription();
        from = experience.getStartDate();
        to = experience.getEndDate();
    }
}
