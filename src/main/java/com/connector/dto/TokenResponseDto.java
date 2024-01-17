package com.connector.dto;

import lombok.Getter;

@Getter
public class TokenResponseDto {
    private String token;

    
    /* xxxResponseDto가 아닌 ResponseDto 라는 이름으로 통일해서 하나의 클래스로만 활용 해도 괜찮지 않은가 라는 생각*/
    public TokenResponseDto(String token) {
        this.token = token;
    }
}
