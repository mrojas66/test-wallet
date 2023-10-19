package com.nequi.msfranchise.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class AppConfig {


    @Value("${api.docs.endpoint}")
    private String endpointUrl;

    @Value("${api.docs.env}")
    private String environment;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .servers(
                        Arrays.asList(
                                new Server().url(endpointUrl).description("Servidor de Desarrollo")
                        )
                )
                .info(
                new Info()
                        .title("Nequi - Franchise - "+environment)
                        .version("0.1")
                        .description("Api spring boot 3 con swagger")
        );
    }

}
