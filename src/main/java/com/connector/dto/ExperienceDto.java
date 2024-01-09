package com.connector.dto;

import com.connector.domain.Experience;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExperienceDto {
    private Long id;
    @Schema(description = "회사명", example = "SK Hynix")
    private String company;
    @Schema(description = "업무", example = "Backend Developer")
    private String position;
    @Schema(description = "설명", example = "Backend")
    private String description;
    @Schema(description = "시작일")
    private LocalDate from;
    @Schema(description = "종료일")
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
