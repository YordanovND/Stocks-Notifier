package com.nikolayyordanov.stocks.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StockMeta {
    private String symbol;
    private String currency;
    private BigDecimal regularMarketPrice;
    private long regularMarketTime;
}
