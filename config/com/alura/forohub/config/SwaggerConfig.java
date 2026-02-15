package com.alura.forohub.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI foroHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Foro Hub API")
                        .description("Documentación de la API del Foro Hub")
                        .version("1.0"));
    }
}
