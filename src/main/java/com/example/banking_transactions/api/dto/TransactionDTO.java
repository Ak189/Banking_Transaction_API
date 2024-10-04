package com.example.banking_transactions.api.dto;

public class TransactionDTO {

    private Long toAccountId;

    private double amount;

    public TransactionDTO(Long toAccountId, double amount){
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public Long getToAccountId(){
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

}
