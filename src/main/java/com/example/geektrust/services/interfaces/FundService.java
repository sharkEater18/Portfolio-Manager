package com.example.geektrust.services.interfaces;

import com.example.geektrust.pojos.Fund;

public interface FundService {
    void addStock(String fundName, String stockName);
    Fund getFundByName(String fundName);
}
