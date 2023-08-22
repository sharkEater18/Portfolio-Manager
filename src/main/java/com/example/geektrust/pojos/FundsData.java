package com.example.geektrust.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FundsData {
    @JsonProperty("funds")
    private List<Fund> funds;

    public FundsData() {
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }
}
