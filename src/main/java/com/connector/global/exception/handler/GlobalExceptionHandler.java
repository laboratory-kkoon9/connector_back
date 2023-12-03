package com.connector.global.exception.handler;

import com.connector.global.exception.NotProfileException;
import com.connector.global.exception.NotUserException;
import com.connector.global.response.ErrorDetailResponse;
import com.connector.global.response.ErrorResponse;
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
    @ExceptionHandler(NotUserException.class)
    protected ResponseEntity<?> handleBadRequestException(NotUserException e) {
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
                .errors(Arrays.asList(detailResponse))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotProfileException.class)
    protected ResponseEntity<?> handleBadRequestException(NotProfileException e) {
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
                .errors(Arrays.asList(detailResponse))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
