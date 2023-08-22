package com.example.geektrust.pojos;

public class OverlapData {
    private String fundName;
    private String overlapFundName;
    private double overlapPercentage;

    public OverlapData(String fundName, String overlapFundName, double overlapPercentage) {
        this.fundName = fundName;
        this.overlapFundName = overlapFundName;
        this.overlapPercentage = overlapPercentage;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getOverlapFundName() {
        return overlapFundName;
    }

    public void setOverlapFundName(String overlapFundName) {
        this.overlapFundName = overlapFundName;
    }

    public double getOverlapPercentage() {
        return overlapPercentage;
    }

    public void setOverlapPercentage(double overlapPercentage) {
        this.overlapPercentage = overlapPercentage;
    }
}
