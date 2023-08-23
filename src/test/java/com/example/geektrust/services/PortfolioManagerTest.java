package com.example.geektrust.services;

import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.services.interfaces.UserPortfolioService;
import com.example.geektrust.utils.FundLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortfolioManagerTest {

    private FundService fundService;
    private UserPortfolioService userPortfolioService;
    private PortfolioManagerImpl portfolioManager;

    @BeforeEach
    public void setUp() {
        fundService = new FundServiceImpl(new FundLoader());
        userPortfolioService = new UserPortfolioServiceImpl(fundService);
        portfolioManager = new PortfolioManagerImpl(fundService, userPortfolioService);
    }

    @Test
    public void testManagePortfolio() throws FileNotFoundException {
        portfolioManager.managePortfolio("sample_input/input1.txt");
        assertTrue(fundService.getFundByName("AXIS_BLUECHIP").getStockNameList().contains("TCS_LT"));
    }
}

