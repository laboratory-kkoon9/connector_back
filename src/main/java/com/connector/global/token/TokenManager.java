package com.connector.global.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.connector.global.exception.UsernameFromTokenException;
import com.connector.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenManager {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.issuer}")
    private String jwtIssuer;

    public final static Long LOCAL_ACCESS_TOKEN_TIME_OUT = 9999999L * 60 * 60;
    public final static Long ACCESS_TOKEN_TIME_OUT = 1000L * 60 * 60; // 1시간

    public String generateToken(TokenDto tokenDto) {
        return newToken(tokenDto, ACCESS_TOKEN_TIME_OUT); // 1일
    }

    private String newToken(TokenDto token, Long expireTime) {
        return JWT.create()
                .withClaim("user_id", token.getUserId())
                .withIssuedAt(new Date())
                .withIssuer(jwtIssuer)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    public Claim getJwtClaims(String token) {
        DecodedJWT jwt = validateToken(token);
        return jwt.getClaim("user_id");
    }

    /**
     * Jwt Token 검증한다.
     * 토큰 만료시 우선 true로 리턴하고 이후 로직에서 RefreshToken을 검증한다.
     *
     * @param token
     */
    public DecodedJWT validateToken(String token) {
        if (ObjectUtils.isEmpty(token)) {
            throw new UsernameFromTokenException("JWT Empty. Please check header.");
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withIssuer("wonchul")
                .build();
        return verifier.verify(token);
    }
}
