package com.connector.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ExperienceDto {
    private String title;
    private String company;
    private String location;
    private LocalDate from;
    private LocalDate to;
    private Boolean current;
    private String description;

    public ExperienceDto(String title, String company, String location, LocalDate from, LocalDate to, Boolean current, String description) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.from = from;
        this.to = to;
        this.current = current;
        this.description = description;
    }
}
