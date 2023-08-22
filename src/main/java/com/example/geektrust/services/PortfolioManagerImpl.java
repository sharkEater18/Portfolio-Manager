package com.example.geektrust.services;

import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.services.interfaces.PortfolioManager;
import com.example.geektrust.services.interfaces.UserPortfolioService;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PortfolioManagerImpl implements PortfolioManager {
    private FundService fundService;
    private UserPortfolioService userPortfolioService;

    public PortfolioManagerImpl(FundService fundService, UserPortfolioService userPortfolioService) {
        this.fundService = fundService;
        this.userPortfolioService = userPortfolioService;
    }

    @Override
    public void managePortfolio(String inputFilePath) {
        try {
            FileInputStream fis = new FileInputStream(inputFilePath);
            Scanner scanner = new Scanner(fis);
            String input;

            while (!(input = scanner.nextLine()).isEmpty()) {
                String[] tokens = input.split(" ");
                String command = tokens[0];

                switch (command) {
                    case "CURRENT_PORTFOLIO":
                        List<String> fundNames = new ArrayList<>();
                        for (int i = 1; i < tokens.length; i++) {
                            fundNames.add(tokens[i]);
                        }
                        userPortfolioService.currentPortfolio(fundNames);
                        break;

                    case "CALCULATE_OVERLAP":
                        String fundName = tokens[1];
                        userPortfolioService.calculateOverlap(fundName);
                        break;

                    case "ADD_STOCK":
                        String fundToAddStock = tokens[1];
                        String stockName = input.substring(command.length() + fundToAddStock.length() + 2);
                        fundService.addStock(fundToAddStock, stockName);
                        break;

                    default:
                        System.out.println("Invalid command !!");
                        break;
                }
            }
            scanner.close();
        } catch (Exception ignored) {
        }
    }
}
