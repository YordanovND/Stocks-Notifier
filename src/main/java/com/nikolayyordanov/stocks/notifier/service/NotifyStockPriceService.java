package com.nikolayyordanov.stocks.notifier.service;

import com.nikolayyordanov.stocks.notifier.client.TelegramClient;
import com.nikolayyordanov.stocks.notifier.client.YahooClient;
import com.nikolayyordanov.stocks.notifier.model.StockMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

@Service
@RequiredArgsConstructor
public class NotifyStockPriceService implements CommandLineRunner {

    private final YahooClient yahooClient;
    private final TelegramClient telegramClient;

    private static final String ZONE_ID = "Europe/Sofia";
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    @Override
    public void run(String... args) throws Exception {
        sendNotification();
    }

    //    @Scheduled(
    //            cron = "0 0 9 * * *",
    //            zone = ZONE_ID
    //    )
    public void sendNotification() {
        StockMeta stockQuoteMetadata = yahooClient.getQuote();

        String formattedDate = Instant.ofEpochSecond(stockQuoteMetadata.getRegularMarketTime())
                .atZone(ZoneId.of(ZONE_ID))
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT));

        String price = stockQuoteMetadata.getRegularMarketPrice().setScale(2, RoundingMode.HALF_UP).toPlainString();

        String currencySymbol = Currency.getInstance(stockQuoteMetadata.getCurrency()).getSymbol();

        String message = stockQuoteMetadata.getSymbol() + " current price is -> "
                + currencySymbol + price  + ".\nLast update time is: " + formattedDate;

        telegramClient.send(message);
    }
}
