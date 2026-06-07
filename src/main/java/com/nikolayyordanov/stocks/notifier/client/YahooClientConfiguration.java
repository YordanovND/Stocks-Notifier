package com.nikolayyordanov.stocks.notifier.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolayyordanov.stocks.notifier.config.YahooProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class YahooClientConfiguration {

    private final YahooProperties properties;

    @Bean
    HttpClient yahooHttpClient() {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @Bean
    YahooClient yahooClient(
            HttpClient yahooHttpClient,
            ObjectMapper objectMapper
    ) {
        return new YahooClient(
                yahooHttpClient,
                objectMapper,
                properties
        );
    }
}