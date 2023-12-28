package com.connector.dto;

import com.connector.domain.Experience;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExperienceDto {
    private Long id;
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;

    public static ExperienceDto getExperienceDto(Experience experience) {
        return new ExperienceDto(
                experience.getId(),
                experience.getCompany(),
                experience.getPosition(),
                experience.getDescription(),
                experience.getStartDate(),
                experience.getEndDate());
    }

    public Experience toEntity() {
        return Experience.builder()
                .company(company)
                .position(position)
                .description(description)
                .startDate(from)
                .endDate(to)
                .build();
    }
}
