package com.nikolayyordanov.stocks.notifier.controller;

import com.nikolayyordanov.stocks.notifier.client.YahooClient;
import com.nikolayyordanov.stocks.notifier.model.StockMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final YahooClient yahooClient;

    @GetMapping("/getQuote")
    public StockMeta getQuote() {
       return yahooClient.getQuote();
    }
}
