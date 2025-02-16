package com.connector.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"local", "develop"})
@Configuration
@OpenAPIDefinition(servers = {@io.swagger.v3.oas.annotations.servers.Server(url = "/", description = "API Server")})
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi customTestOpenAPi() {
        return GroupedOpenApi
                .builder()
                .group("APIS")
                .addOpenApiCustomiser(buildSecurityOpenApi())
                .build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        return openApi -> openApi.addSecurityItem(new SecurityRequirement().addList("x-auth-token"))
            .getComponents()
            .addSecuritySchemes("x-auth-token", new SecurityScheme()
                .name("x-auth-token") // 변경된 헤더명
                .type(SecurityScheme.Type.APIKEY) // HTTP → APIKEY 변경
                .in(SecurityScheme.In.HEADER));
    }

}
