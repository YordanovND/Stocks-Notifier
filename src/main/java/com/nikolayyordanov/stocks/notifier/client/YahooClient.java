package com.nikolayyordanov.stocks.notifier.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nikolayyordanov.stocks.notifier.config.YahooProperties;
import com.nikolayyordanov.stocks.notifier.model.StockMeta;
import com.nikolayyordanov.stocks.notifier.model.StockQuote;
import com.nikolayyordanov.stocks.notifier.model.StockResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class YahooClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final YahooProperties properties;

    public StockMeta getQuote() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(properties.baseUrl() + "/v8/finance/chart/" + properties.followSymbol()))
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            StockQuote quote = objectMapper.readValue(response.body(), StockQuote.class);

            return quote.getChart()
                    .getResult()
                    .stream()
                    .findFirst()
                    .map(StockResult::getMeta)
                    .orElseThrow(() -> new IllegalStateException("No quote returned"));

        } catch (Exception ex) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "Failed to load quote");
        }
    }
}