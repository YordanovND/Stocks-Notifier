package com.nikolayyordanov.stocks.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockChart {
    private List<StockResult> result = List.of();
    private String error;
}
