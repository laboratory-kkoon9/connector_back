package com.connector.global.filter;

import com.connector.global.token.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final static String AUTHORIZATION_HEADER = "x-auth-token";
    private final AntPathMatcher pathMatcher = new AntPathMatcher();


    @Value("${except-uri}")
    private String exceptURI;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, NullPointerException {

        // 1) 토큰 조회
        String accessToken = this.resolveToken(request);

        // 2) 토큰 벨리데이션
        tokenManager.validateToken(accessToken);

        filterChain.doFilter(request, response);
    }

    /**
     * HTTP Header > bearerToken 추출
     *
     * @param request
     */
    private String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        if ("GET".equals(method) && pathMatcher.match("/api/profile", path)) return true;
        if ("POST".equals(method) && pathMatcher.match("/api/profile/image", path)) return true;
        if ("GET".equals(method) && pathMatcher.match("/api/profile/user/**", path)) return true;
        if ("GET".equals(method) && pathMatcher.match("/api/follow/user/**", path)) return true;
        if ("GET".equals(method) && pathMatcher.match("/api/profile/github/**", path)) return true;
        if ("POST".equals(method) && pathMatcher.match("/api/auth", path)) return true;
        if ("POST".equals(method) && pathMatcher.match("/api/users", path)) return true;

        return Arrays.stream(exceptURI.split(","))
            .anyMatch(pattern -> pathMatcher.match(pattern.trim(), path));
    }

}
