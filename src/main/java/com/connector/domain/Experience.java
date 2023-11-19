package com.connector.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Experience {
    private String company;
    private String position;
    private String description;
    private LocalDate from;
    private LocalDate to;

    @Builder
    public Experience(String company, String position, String description, LocalDate from, LocalDate to) {
        this.company = company;
        this.position = position;
        this.description = description;
        this.from = from;
        this.to = to;
    }
}
