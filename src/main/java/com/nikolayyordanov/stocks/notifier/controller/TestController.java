package com.nikolayyordanov.stocks.notifier.controller;

import com.nikolayyordanov.stocks.notifier.service.NotifyStockPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final NotifyStockPriceService notifyStockPriceService;

    @GetMapping("/getQuote")
    public void getQuote() {
       notifyStockPriceService.sendNotification();
    }
}
