package com.nikolayyordanov.stocks.notifier.client;

import com.nikolayyordanov.stocks.notifier.config.TelegramProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class TelegramClient {
    private final HttpClient httpClient;
    private final TelegramProperties properties;

    public void send(String message) {
        try {
            String encodedMessage =
                    URLEncoder.encode(message, StandardCharsets.UTF_8);

            URI uri = URI.create(
                    properties.baseUrl()
                    + properties.botToken()
                    + "/sendMessage?chat_id="
                    + properties.chatId()
                    + "&text="
                    + encodedMessage
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.discarding()
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to send Telegram message", e);
        }
    }
}
