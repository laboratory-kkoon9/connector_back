package com.connector.dto;

import com.connector.domain.Experience;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
public class ExperienceDto {
    private String title;
    private String company;
    private String position;
    private LocalDate from;
    private LocalDate to;
    private Boolean current;
    private String description;

    public ExperienceDto(String title, String company, String position, LocalDate from, LocalDate to, Boolean current, String description) {
        this.title = title;
        this.company = company;
        this.position = position;
        this.from = from;
        this.to = to;
        this.current = current;
        this.description = description;
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
