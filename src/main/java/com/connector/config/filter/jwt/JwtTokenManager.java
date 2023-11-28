package com.connector.config.filter.jwt;

import com.connector.config.exception.UsernameFromTokenException;
import com.connector.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {
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
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return Jwts.builder()
                .setIssuedAt(new Date())
                .claim("user_id", token.getUserId())
                .setIssuer(jwtIssuer)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();
    }

    /**
     * JWT Claim 가져오기
     *
     * @param jwtSecret
     * @param token
     * @return Claims
     */
    public Claims getJwtClaims(String jwtSecret, String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Jwt Token 검증한다.
     * 토큰 만료시 우선 true로 리턴하고 이후 로직에서 RefreshToken을 검증한다.
     *
     * @param token
     */
    public void validateToken(String token) {
        if (ObjectUtils.isEmpty(token)) {
            throw new UsernameFromTokenException("JWT Empty. Please check header.");
        }

        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException e) {
            throw new UsernameFromTokenException("Not Sign. Please check header.");
        } catch (UnsupportedJwtException e) {
            throw new UsernameFromTokenException("Not Found JWT token type. Please check header.: ");
        } catch (IllegalArgumentException e) {
            throw new UsernameFromTokenException("Wrong JWT token. Please check header.");
        } catch (ExpiredJwtException ignored) {
        } catch (Exception e) {
            throw new UsernameFromTokenException("[JWT Token Filter Error]: " + e.getMessage());
        }
    }
}
