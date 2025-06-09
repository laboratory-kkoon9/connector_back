package com.connector.global.config;

import com.connector.global.filter.ExceptionHandlerFilter;
import com.connector.global.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    /**
     * CORS 필터 등록
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0); // 가장 먼저 실행
        return bean;
    }

    /**
     * ExceptionHandlerFilter 등록
     */
    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilterBean() {
        FilterRegistrationBean<ExceptionHandlerFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(exceptionHandlerFilter);
        registration.addUrlPatterns("/api/*");
        registration.setOrder(1); // 두 번째 실행
        return registration;
    }

    /**
     * JWT 필터 등록
     */
    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterBean() {
        FilterRegistrationBean<JwtTokenFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(jwtTokenFilter);
        registration.addUrlPatterns("/api/*");
        registration.setOrder(2); // 세 번째 실행
        return registration;
    }
}
