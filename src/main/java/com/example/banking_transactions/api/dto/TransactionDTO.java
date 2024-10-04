package com.example.banking_transactions.api.dto;
//Data transfer object class to handle Transaction details
public class TransactionDTO {
    //Field for receiver's account
    private Long toAccountId;
    //Field for the amount to be transferred
    private double amount;
    //Constructor class
    public TransactionDTO(Long toAccountId, double amount){
        this.toAccountId = toAccountId;
        this.amount = amount;
    }
    //Getter method to retrieve the ID of the receiver's account.
    public Long getToAccountId(){
        return toAccountId;
    }
    //Getter method to retrieve the amount to be transferred.
    public double getAmount() {
        return amount;
    }

}
