package com.example.geektrust.services;

import com.example.geektrust.pojos.OverlapData;
import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.services.interfaces.UserPortfolioService;
import com.example.geektrust.utils.FundLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserPortfolioServiceTest {
    private FundService fundService;
    private FundLoader fundLoader;
    private UserPortfolioService userPortfolioService;

    public static final String FUND_NAME = "ICICI_PRU_NIFTY_NEXT_50_INDEX";
    public static final int STOCK_LIST_LENGTH = 51;
    public static final String STOCK_NAME = "TEST STOCK";
    public static final List<String> INPUT_FUNDS = Arrays.asList("UTI_NIFTY_INDEX", "AXIS_MIDCAP", "PARAG_PARIKH_FLEXI_CAP");
    private static final List<OverlapData> EXPECTED_OUTPUT = Arrays.asList(new OverlapData("ICICI_PRU_NIFTY_NEXT_50_INDEX", "UTI_NIFTY_INDEX", 20.37),
            new OverlapData("ICICI_PRU_NIFTY_NEXT_50_INDEX", "AXIS_MIDCAP", 14.81),
            new OverlapData("ICICI_PRU_NIFTY_NEXT_50_INDEX", "PARAG_PARIKH_FLEXI_CAP", 7.41));

    @BeforeEach
    public void setup() {
        fundLoader = new FundLoader();
        fundService = new FundServiceImpl(fundLoader);

        userPortfolioService = new UserPortfolioServiceImpl(fundService);
    }

    @Test
    public void testSelectedFunds() {
        userPortfolioService.currentPortfolio(Arrays.asList(FUND_NAME));

        assertEquals(Collections.singletonList(FUND_NAME), userPortfolioService.getSelectedFunds());
        assertEquals(Collections.emptyList(), userPortfolioService.calculateOverlap(FUND_NAME));
    }

    @Test
    public void testCalculateOverlap() {
        userPortfolioService.currentPortfolio(INPUT_FUNDS);

        List<OverlapData> actualOutput = userPortfolioService.calculateOverlap(FUND_NAME);

        assertEquals(EXPECTED_OUTPUT.get(0).getFundName(), actualOutput.get(0).getFundName());
        assertEquals(EXPECTED_OUTPUT.size(), actualOutput.size());
        assertEquals(String.format("%.2f%%", EXPECTED_OUTPUT.get(0).getOverlapPercentage()), String.format("%.2f%%", actualOutput.get(0).getOverlapPercentage()));
        assertEquals(EXPECTED_OUTPUT.get(0).getOverlapFundName(), actualOutput.get(0).getOverlapFundName());
    }

    @Test
    public void testCalculateOverlapFundName() {
        userPortfolioService.currentPortfolio(INPUT_FUNDS);
        List<OverlapData> actualOutput = userPortfolioService.calculateOverlap(FUND_NAME);

        assertEquals(EXPECTED_OUTPUT.get(0).getFundName(), actualOutput.get(0).getFundName());
    }

    @Test
    public void testCalculateOverlapSize() {
        userPortfolioService.currentPortfolio(INPUT_FUNDS);
        List<OverlapData> actualOutput = userPortfolioService.calculateOverlap(FUND_NAME);

        assertEquals(EXPECTED_OUTPUT.size(), actualOutput.size());
    }

    @Test
    public void testCalculateOverlapPercentage() {
        userPortfolioService.currentPortfolio(INPUT_FUNDS);
        List<OverlapData> actualOutput = userPortfolioService.calculateOverlap(FUND_NAME);

        assertEquals(String.format("%.2f%%", EXPECTED_OUTPUT.get(0).getOverlapPercentage()),
                String.format("%.2f%%", actualOutput.get(0).getOverlapPercentage()));
    }

    @Test
    public void testCalculateOverlapOverlapFundName() {
        userPortfolioService.currentPortfolio(INPUT_FUNDS);

        List<OverlapData> actualOutput = userPortfolioService.calculateOverlap(FUND_NAME);

        assertEquals(EXPECTED_OUTPUT.get(0).getOverlapFundName(), actualOutput.get(0).getOverlapFundName());
    }

}
