package com.example.banking_transactions.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



public class AccountDTO {
    @NotNull(message = "Name cannot be null")
    private String name;
    @Min(value = 0, message = "Initial balance must be positive")
    private double initialBalance;

    public AccountDTO() {}

    public AccountDTO(String name, double initialBalance){
        this.name = name;
        this.initialBalance = initialBalance;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(double initialBalance){
        this.initialBalance = initialBalance;
    }
}
