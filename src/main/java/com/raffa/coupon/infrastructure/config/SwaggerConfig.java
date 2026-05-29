package com.raffa.coupon.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Coupon API")
                        .version("1.0")
                        .description("""
                        API REST para gerenciamento de cupons.
                                        
                        Funcionalidades:
                        - Criação de cupons
                        - Soft delete
                        - Regras de negócio encapsuladas
                        """)
                );
    }
}