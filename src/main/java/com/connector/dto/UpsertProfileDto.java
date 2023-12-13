package com.connector.dto;

import lombok.Getter;

@Getter
public class UpsertProfileDto {
    private String company;
    private String website;
    private String location;
    private String bio;
    private String status;
    private String skills;
}
