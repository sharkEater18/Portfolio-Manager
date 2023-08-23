package com.example.geektrust.services;

import com.example.geektrust.pojos.Fund;
import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.utils.FundLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FundServiceTest {
    private FundService fundService;
    private FundLoader fundLoader;

    public static final String FUND_NAME = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
    public static final int STOCK_LIST_LENGTH = 51;
    public static final String STOCK_NAME = "TEST STOCK";

    @BeforeEach
    public void setup() {
        fundLoader = new FundLoader();
        fundService = new FundServiceImpl(fundLoader);
    }

    @Test
    public void testAddStock() {
        fundService.addStock(FUND_NAME, STOCK_NAME);
        Fund fund = fundService.getFundByName(FUND_NAME);

        assertEquals(STOCK_LIST_LENGTH + 1, fund.getStockList().size());
        assertEquals(STOCK_NAME, fund.getStockList().get(fund.getStockList().size() - 1).getStockName());;
    }

    @Test
    public void testAddStockToInvalidFund() {
        String non_existent_fund = FUND_NAME + "!";
        fundService.addStock(non_existent_fund, STOCK_NAME);
        Fund fund = fundService.getFundByName(non_existent_fund);

        assertNull(fund);
    }
}
