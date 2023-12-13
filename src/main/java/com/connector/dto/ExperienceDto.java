package com.connector.dto;

import com.connector.domain.Experience;
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

    public static ExperienceDto getExperienceDto(Experience experience) {
        return new ExperienceDto(
                experience.getCompany(),
                experience.getPosition(),
                experience.getDescription(),
                experience.getStartDate(),
                experience.getEndDate());
    }
}
