package com.connector.global.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorDetailResponse {
    private String msg;

    @Builder
    public ErrorDetailResponse(String msg) {
        this.msg = msg;
    }
}
