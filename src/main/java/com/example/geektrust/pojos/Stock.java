package com.example.geektrust.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stock {
    @JsonProperty
    private String stockName;

    public Stock(String stockName) {
        this.stockName = stockName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockName='" + stockName + '\'' +
                '}';
    }
}
