package com.example.janghakmoney.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Janghakmoney API")
                        .description("장학금 매칭 프로젝트 API 문서")
                        .version("v1.0.0"));
    }
}
