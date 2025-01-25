package com.connector.dto;

import com.connector.domain.Experience;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "경력 조회 DTO")
public class GetExperienceDto {
    @Schema(description = "경력 id")
    private Long id;
    @Schema(description = "회사")
    private String company;
    @Schema(description = "직무")
    private String position;
    @Schema(description = "직무 설명")
    private String description;
    @Schema(description = "경력 시작 날짜")
    private LocalDate from;
    @Schema(description = "경력 종료 날짜")
    private LocalDate to;

    @Builder
    public GetExperienceDto(Long id, String company, String position, String description, LocalDate from, LocalDate to) {
        this.id = id;
        this.company = company;
        this.position = position;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public static GetExperienceDto from(Experience experience) {
        return GetExperienceDto.builder()
                .id(experience.getId())
                .company(experience.getCompany())
                .position(experience.getPosition())
                .description(experience.getDescription())
                .from(experience.getStartDate())
                .to(experience.getEndDate())
                .build();
    }

}
