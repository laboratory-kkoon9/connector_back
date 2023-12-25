package com.connector.service;

import com.connector.dto.GithubResponseItemDto;
import com.connector.global.exception.BadRequestException;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.util.List;

@Slf4j
@Service
public class GithubClient {
    private static final String PATH_REQUIRED_MESSAGE = "PATH 파라미터는 필수값입니다";
    private static final String GITHUB_CLIENT_ERROR_MESSAGE = "깃허브 클라이언트 에러입니다.";
    private static final String GITHUB_SERVER_ERROR_MESSAGE = "깃허브 서버 에러입니다.";

    private final WebClient webClient;

    @Value("${github.base-url}")
    private String GITHUB_BASE_URL;

    @Value("${github.token}")
    private String GITHUB_TOKEN;

    public GithubClient() {
        webClient = WebClient.builder()
                .build();
    }


    private ResponseSpec createGetRequest(String path) {
        if (StringUtil.isNullOrEmpty(path)) {
            throw new BadRequestException(PATH_REQUIRED_MESSAGE);
        }
        return webClient
                .get()
                .uri(GITHUB_BASE_URL + path)
                .headers(httpHeaders -> {
                    httpHeaders.add("Accept", "application/vnd.github+json");
                    httpHeaders.add("Authorization", "Bearer " + GITHUB_TOKEN);
                    httpHeaders.add("X-GitHub-Api-Version", "2022-11-28");
                        }
                )
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    throw new BadRequestException(GITHUB_CLIENT_ERROR_MESSAGE);
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    throw new BadRequestException(GITHUB_SERVER_ERROR_MESSAGE);
                });
    }

    public List<GithubResponseItemDto> getUserRepositories(String githubId) {
        String path = String.format("/users/%s/repos?per_page=4&sort=created", githubId);
        List<GithubResponseItemDto> response = createGetRequest(path)
                .bodyToFlux(GithubResponseItemDto.class)
                .collectList()
                .block();
        return response;
    }
}
