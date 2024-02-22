package ru.tinkoff.notes.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(name = "basic", type = SecuritySchemeType.HTTP, scheme = "basic", bearerFormat = "Basic")
@Configuration
public class SpringdocConfig {
}
