package com.connector.config.filter;

import com.connector.config.filter.jwt.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    @Value("${except-uri}")
//    private String exceptURI;
//    private final JwtTokenManager jwtTokenManager;
//    private final static String AUTHORIZATION_HEADER = "x-auth-token";
//    private final static String BEARER_PREFIX = "Bearer ";
//    private final static String REFRESH_TOKEN_NAME = "refreshToken";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, NullPointerException {
//
//        // 1) 토큰 조회
//        String accessToken = this.resolveToken(request);
//
//        // 2) 토큰 벨리데이션
//        jwtTokenManager.validateToken(accessToken);
//
//        // 3) Authentication SecurityContext 저장
//        Authentication authentication = jwtTokenManager.getAuthentication(accessToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // 4) ContextHolder 어디서 접근했는지 구분자
//        ProductSupplyContextHolder.getContext().setApplicationDivision(CommonConstants.SUPPLER_CENTER);
//
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * HTTP Header > bearerToken 추출
//     *
//     * @param request
//     */
//    private String resolveToken(HttpServletRequest request) {
//        try {
//            String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//                return bearerToken.substring(BEARER_PREFIX.length());
//            }
//        } catch (NullPointerException e) {
//            SecurityContextHolder.clearContext();
//            throw new UsernameFromTokenException(EMPTY_JWT_TOKEN);
//        }
//        return null;
//    }
//}
