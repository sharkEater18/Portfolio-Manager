package com.example.geektrust.services;

import com.example.geektrust.pojos.Fund;
import com.example.geektrust.pojos.FundsData;
import com.example.geektrust.pojos.Stock;
import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.utils.FundLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.geektrust.constants.Constants.FUND_NOT_FOUND;

public class FundServiceImpl implements FundService {
    private Map<String, Fund> fundMap;

    public FundServiceImpl(FundLoader fundLoader) {
        this.fundMap = new HashMap<>();
        FundsData fundsData = fundLoader.loadFunds();
        loadFundsFromData(fundsData);
    }

    private void loadFundsFromData(FundsData fundsData) {
        for (Fund fundData : fundsData.getFunds()) {
            fundData.setFundId(UUID.randomUUID().toString());
            fundMap.put(fundData.getFundName(), fundData);
        }
    }

    @Override
    public void addStock(String fundName, String stockName) {
        Fund fund = fundMap.get(fundName);
        if (fund != null) {
            Stock newStock = new Stock(stockName);
            fund.addStock(newStock);
        } else {
            System.out.println(FUND_NOT_FOUND);
        }
    }

    @Override
    public Fund getFundByName(String fundName) {
        return fundMap.get(fundName);
    }
}
