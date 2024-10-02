package com.example.banking_transactions.api.dto;



public class AccountDTO {
    private String name;
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
