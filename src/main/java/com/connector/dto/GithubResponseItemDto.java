package com.connector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubResponseItemDto {
    private Long id;
    private String name;
    private String description;
    @JsonProperty(value="html_url")
    private String repositoryUrl;
    @JsonProperty(value="stargazers_count")
    private Integer starCount;
    @JsonProperty(value="watchers_count")
    private Integer watcherCount;
    @JsonProperty(value="forks_count")
    private Integer forkCount;
}
