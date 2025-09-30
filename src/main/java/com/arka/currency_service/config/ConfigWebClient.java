package com.arka.currency_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    @Bean
    public WebClient getCurrencyWebClient(WebClient.Builder builder){
        return builder
                .baseUrl("https://v6.exchangerate-api.com/v6")
                .build();
    }
}
