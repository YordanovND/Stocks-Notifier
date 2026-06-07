package com.nikolayyordanov.stocks.notifier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http.yahoo")
public record YahooProperties(
        String baseUrl,
        String followSymbol
) {
}