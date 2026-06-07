package com.nikolayyordanov.stocks.notifier.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram")
public record TelegramProperties(
    String baseUrl,
    String botToken,
    String chatId
){
}
