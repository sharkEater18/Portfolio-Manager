package com.example.geektrust.services;

import com.example.geektrust.pojos.Fund;
import com.example.geektrust.pojos.OverlapData;
import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.services.interfaces.UserPortfolioService;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.geektrust.constants.Constants.FUND_NOT_FOUND;

public class UserUserPortfolioServiceImpl implements UserPortfolioService {
    private FundService fundService;
    private List<String> selectedFunds;

    public UserUserPortfolioServiceImpl(FundService fundService) {
        this.fundService = fundService;
        this.selectedFunds = new ArrayList<>();
    }

    @Override
    public void currentPortfolio(List<String> fundNames) {
        selectedFunds.addAll(fundNames.stream()
                .map(fundService::getFundByName)
                .filter(Objects::nonNull)
                .map(Fund::getFundName)
                .collect(Collectors.toList()));
    }

    @Override
    public List<OverlapData> calculateOverlap(String fundName) {
        Fund selectedFund = fundService.getFundByName(fundName);
        if (selectedFund == null) {
            System.out.println(FUND_NOT_FOUND);
            return Collections.emptyList();
        }

        List<String> selectedFundStocks = selectedFund.getStockNameList();
        return getSelectedFunds().stream()
                .filter(otherFundName -> !otherFundName.equals(fundName))
                .map(otherFundName -> {
                    Fund otherFund = fundService.getFundByName(otherFundName);
                    double overlapPercentage = calculateOverlapPercentage(selectedFundStocks, otherFund.getStockNameList());
                    return new OverlapData(fundName, otherFundName, overlapPercentage);
                })
                .filter(data -> data.getOverlapPercentage() > 0.0D)
                .peek(data -> {
                    String formattedOverlapPercentage = String.format("%.2f%%", data.getOverlapPercentage());
                    System.out.println(data.getFundName() + " " + data.getOverlapFundName() + " " + formattedOverlapPercentage);
                })
                .collect(Collectors.toList());
    }

    private double calculateOverlapPercentage(List<String> stocks1, List<String> stocks2) {
        Set<String> commonStocks = new HashSet<>(stocks1);
        commonStocks.retainAll(new HashSet<>(stocks2));

        int commonStockCount = commonStocks.size();
        int totalStocksCount = stocks1.size() + stocks2.size();

        return (double) (2 * commonStockCount) / totalStocksCount * 100;
    }

    public List<String> getSelectedFunds() {
        return selectedFunds;
    }

    public void setSelectedFunds(List<String> selectedFunds) {
        this.selectedFunds = selectedFunds;
    }
}
