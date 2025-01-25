package com.connector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "깃허브의 레포지터리 목록 조회 요청 DTO")
public class GithubResponseItemDto {
    @Schema(description = "레포지터리 id")
    private Long id;
    @Schema(description = "레포지터리 이름")
    private String name;
    @Schema(description = "레포지터리 설명")
    private String description;
    @JsonProperty(value="html_url")
    @Schema(description = "레포지터리 url")
    private String repositoryUrl;
    @JsonProperty(value="stargazers_count")
    @Schema(description = "레포지터리 star 개수")
    private Integer starCount;
    @Schema(description = "레포지터리 watcher 개수")
    @JsonProperty(value="watchers_count")
    private Integer watcherCount;
    @Schema(description = "레포지터리 fork 개수")
    @JsonProperty(value="forks_count")
    private Integer forkCount;
}
