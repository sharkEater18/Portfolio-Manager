package com.example.geektrust.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Fund {
    private String fundId;

    @JsonProperty("name")
    private String fundName;

    @JsonProperty("stocks")
    private List<Stock> stockList;

    public Fund() {};

    public Fund(String fundId, String fundName, List<Stock> stockList) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.stockList = stockList;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public List<String> getStockNameList() {
        return this.stockList.stream()
                .map(Stock::getStockName)
                .collect(Collectors.toList());
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public void addStock(Stock stock) {
        stockList.add(stock);
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundId='" + fundId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", stockList=" + stockList +
                '}';
    }
}
