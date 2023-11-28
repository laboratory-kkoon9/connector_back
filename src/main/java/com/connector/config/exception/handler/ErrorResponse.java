package com.connector.config.exception.handler;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private List<ErrorDetailResponse> errors;

    @Builder
    public ErrorResponse(List<ErrorDetailResponse> errors) {
        this.errors = errors;
    }
}
