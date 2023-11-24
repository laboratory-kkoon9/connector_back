package com.connector.dto;

import com.connector.domain.Experience;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetExperienceDto {
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;

    @Builder
    public GetExperienceDto(String company, String position, String description, LocalDate from, LocalDate to) {
        this.company = company;
        this.position = position;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public static GetExperienceDto of(Experience experience) {
        return GetExperienceDto.builder()
                .company(experience.getCompany())
                .position(experience.getPosition())
                .description(experience.getDescription())
                .from(experience.getStartDate())
                .to(experience.getEndDate())
                .build();
    }

}
