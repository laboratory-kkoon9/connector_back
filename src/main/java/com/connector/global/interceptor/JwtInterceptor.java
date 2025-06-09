package com.connector.global.interceptor;

import com.connector.global.token.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final TokenManager tokenManager;
    private final static String AUTHORIZATION_HEADER = "x-auth-token";
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Value("${except-uri}")
    private String exceptURI;

    private final Set<ExcludePath> excludePaths = new LinkedHashSet<>();

    @PostConstruct
    public void initExcludePaths() {
        // 명시적 예외 URI + HTTP 메서드
        excludePaths.add(new ExcludePath("/api/profile", HttpMethod.GET));
        excludePaths.add(new ExcludePath("/api/profile/image", HttpMethod.POST));
        excludePaths.add(new ExcludePath("/api/profile/user/**", HttpMethod.GET));
        excludePaths.add(new ExcludePath("/api/follow/user/**", HttpMethod.GET));
        excludePaths.add(new ExcludePath("/api/profile/github/**", HttpMethod.GET));
        excludePaths.add(new ExcludePath("/api/auth", HttpMethod.POST));
        excludePaths.add(new ExcludePath("/api/users", HttpMethod.POST));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (shouldNotIntercept(request)) {
            return true;
        }

        String accessToken = request.getHeader(AUTHORIZATION_HEADER);
        tokenManager.validateToken(accessToken);
        return true;
    }

    private boolean shouldNotIntercept(HttpServletRequest request) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        for (ExcludePath excludePath : excludePaths) {
            if (excludePath.matches(request)) {
                return true;
            }
        }

        return Arrays.stream(exceptURI.split(","))
            .anyMatch(pattern -> pathMatcher.match(pattern.trim(), request.getServletPath()));
    }

    @RequiredArgsConstructor
    private static class ExcludePath {
        private final String pathPattern;
        private final HttpMethod method;

        public boolean matches(HttpServletRequest request) {
            return method.matches(request.getMethod()) && new AntPathMatcher().match(pathPattern, request.getServletPath());
        }
    }
}
