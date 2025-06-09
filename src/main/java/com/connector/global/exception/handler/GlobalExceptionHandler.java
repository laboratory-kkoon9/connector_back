package com.connector.global.exception.handler;

import com.connector.global.exception.BadRequestException;
import com.connector.global.exception.UsernameFromTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 사용자 정의 예외처리
     */
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        log.error(e.getMessage(), e);
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
                .errors(Arrays.asList(detailResponse))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UsernameFromTokenException.class)
    protected ResponseEntity<?> handleException(UsernameFromTokenException e) {
        log.error(e.getMessage(), e);
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
            .errors(Arrays.asList(detailResponse))
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
            .errors(Arrays.asList(detailResponse))
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
