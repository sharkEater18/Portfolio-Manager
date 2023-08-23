package com.example.geektrust.services.interfaces;

import com.example.geektrust.pojos.OverlapData;

import java.util.List;

public interface UserPortfolioService {
    void currentPortfolio(List<String> fundNames);
    List<OverlapData> calculateOverlap(String fundName);
    List<String> getSelectedFunds();

}
