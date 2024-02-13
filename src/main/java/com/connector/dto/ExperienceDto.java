package com.connector.dto;

import com.connector.domain.Experience;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExperienceDto {
    private Long id;
    private String title;
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;
    private Boolean current;

    @Builder
    public ExperienceDto(Long id, String title, String company, String position, String description, LocalDate from, LocalDate to, Boolean current) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.position = position;
        this.description = description;
        this.from = from;
        this.to = to;
        this.current = current;
    }

    public Experience toEntity(){
        return Experience.builder()
                .id(id)
                .title(title)
                .company(company)
                .position(position)
                .description(description)
                .from(from)
                .to(to)
                .current(current)
                .build();
    }
}
