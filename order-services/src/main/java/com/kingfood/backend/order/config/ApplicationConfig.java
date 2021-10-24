package com.kingfood.backend.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean(name = "okHttpClient")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
