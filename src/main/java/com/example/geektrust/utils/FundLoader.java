package com.example.geektrust.utils;

import com.example.geektrust.pojos.FundsData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.example.geektrust.constants.Constants.FUND_DATA_LOCAL;
import static com.example.geektrust.constants.Constants.FUND_DATA_URL;

public class FundLoader {
    public FundsData loadFunds() {
        ObjectMapper objectMapper = new ObjectMapper();
        FundsData fundsData = null;

        try {
            fundsData = objectMapper.readValue(new URL(FUND_DATA_URL), FundsData.class);
        } catch (Exception ignored) {
        }

        if (fundsData == null) {
            try {
                fundsData = objectMapper.readValue(new File(FUND_DATA_LOCAL), FundsData.class);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to read from local file !!");
            }
        }

        return fundsData;
    }
}

