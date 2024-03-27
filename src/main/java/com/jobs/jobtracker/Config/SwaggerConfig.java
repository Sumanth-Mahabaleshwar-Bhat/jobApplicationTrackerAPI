package com.jobs.jobtracker.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@OpenAPIDefinition (
        info = @Info(
                description = "OpenAPI Swagger documentation for Spring Security",
                title = "OpenAPI Specification"
        ),
        servers = {
                @Server(
                        description = "Local env",
                        url = "http://localhost:8081"
                )
        }
)
public class SwaggerConfig {
}
