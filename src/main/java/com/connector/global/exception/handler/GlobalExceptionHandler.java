package com.connector.global.exception.handler;

import com.connector.global.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

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
        String message = Optional.ofNullable(e.getMessage()).orElseGet(() -> "올바른 요청이 아닙니다. ");
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
                .errors(Arrays.asList(detailResponse))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleWebExchangeBindException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors().get(0).getDefaultMessage();
        ErrorDetailResponse detailResponse = ErrorDetailResponse.builder().msg(message).build();
        ErrorResponse response = ErrorResponse.builder()
                .errors(Arrays.asList(detailResponse))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
