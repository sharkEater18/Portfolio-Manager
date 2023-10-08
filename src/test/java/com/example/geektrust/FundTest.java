package com.example.geektrust;


import com.example.geektrust.pojos.Fund;
import com.example.geektrust.pojos.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;

public class FundTest {
    Fund testFund;

    @BeforeEach
    void setUp() {
        testFund = new Fund();
        List<Stock> stockList = Arrays.asList(new Stock("S1"), new Stock("S2"));
        testFund.setFundId(UUID.randomUUID().toString());
        testFund.setFundName("TEST_FUND");
        testFund.setStockList(stockList);
    }

    @Test
    void testGetStockList() {
        List<String> expectedList = Arrays.asList("S1", "S2");
        assertEquals(expectedList, testFund.getStockNameList());
    }

}
