package com.connector.dto;

import com.connector.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProfileDto {
    private User user;
    private String company;
    private String location;
    private String bio;
    private List<String> skills;
}
