package com.usermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .version("v1")
                        .description("""
                                REST API for managing users in the User Management demo application \
                                (SBA301 - ReactJS & Spring Boot integration).

                                Provides CRUD operations on users plus lookup by id and by email.""")
                        .contact(new Contact().name("Bui Hoa"))
                        .license(new License().name("For educational use")))
                .servers(List.of(new Server().url("http://localhost:8080").description("Local development server")));
    }
}
