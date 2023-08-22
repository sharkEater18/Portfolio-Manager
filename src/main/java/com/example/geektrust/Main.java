package com.example.geektrust;

import com.example.geektrust.services.FundServiceImpl;
import com.example.geektrust.services.PortfolioManagerImpl;
import com.example.geektrust.services.UserUserPortfolioServiceImpl;
import com.example.geektrust.services.interfaces.FundService;
import com.example.geektrust.services.interfaces.PortfolioManager;
import com.example.geektrust.services.interfaces.UserPortfolioService;
import com.example.geektrust.utils.FundLoader;

public class Main {
    public static void main(String[] args) {
        /*
        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
        */

        FundLoader fundLoader = new FundLoader();
        FundService fundService = new FundServiceImpl(fundLoader);
        UserPortfolioService portfolioService = new UserUserPortfolioServiceImpl(fundService);
        PortfolioManager portfolioManager = new PortfolioManagerImpl(fundService, portfolioService);
        portfolioManager.managePortfolio(args[0]);
    }

}
