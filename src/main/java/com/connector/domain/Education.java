package com.connector.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Education {
    private String school;
    private Integer degree;
    private String fieldofstudy;
    private LocalDate from;
    private LocalDate to;

    @Builder
    public Education(String school, Integer degree, String fieldofstudy, LocalDate from, LocalDate to) {
        this.school = school;
        this.degree = degree;
        this.fieldofstudy = fieldofstudy;
        this.from = from;
        this.to = to;
    }
}
