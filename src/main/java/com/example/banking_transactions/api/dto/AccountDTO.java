package com.example.banking_transactions.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

//Data transfer object class for account creation
public class AccountDTO {
    //Name can't be null, using a validation method.
    @NotNull(message = "Name cannot be null")
    private String name;
    //Initial Balance can't be negative.
    private double initialBalance;

    //Constructor class to initiate name and balane.
    public AccountDTO(String name, double initialBalance){
        this.name = name;
        this.initialBalance = initialBalance;
    }

    //Getter method for name.
    public String getName(){
        return name;
    }

    //Getter method for initial balance
    public double getInitialBalance() {
        return initialBalance;
    }

}
