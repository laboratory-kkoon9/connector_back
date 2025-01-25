package com.connector.dto;

import com.connector.domain.Experience;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "경력 추가 요청 DTO")
public class ExperienceDto {
    @Schema(description = "경력 설명")
    private String title;
    @Schema(description = "회사")
    private String company;
    @Schema(description = "직무")
    private String position;
    @Schema(description = "경력 시간 날짜")
    private LocalDate from;
    @Schema(description = "경력 종료 날짜")
    private LocalDate to;
    @Schema(description = "현재 진행 여부")
    private Boolean current;
    @Schema(description = "경력 설명")
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
