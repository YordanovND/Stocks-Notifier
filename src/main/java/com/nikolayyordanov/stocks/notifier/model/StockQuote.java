package com.nikolayyordanov.stocks.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockQuote {
    private StockChart chart;
}