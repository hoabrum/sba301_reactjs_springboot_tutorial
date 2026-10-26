package com.springboot.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_SCHEME = "bearerAuth";

    // Adds an "Authorize" button to Swagger UI so the JWT from /api/auth/signin can be sent as a Bearer token
    @Bean
    public OpenAPI springBootRestApiJwtOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot REST API with JWT")
                        .version("0.0.1")
                        .description("Product REST API secured with JWT and role-based access (USER, MANAGER, ADMIN)"))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_SCHEME))
                .components(new Components().addSecuritySchemes(BEARER_SCHEME,
                        new SecurityScheme()
                                .name(BEARER_SCHEME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
