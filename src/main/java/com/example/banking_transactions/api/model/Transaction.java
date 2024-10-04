package com.example.banking_transactions.api.model;

import java.time.LocalDateTime;

public class Transaction {
    //Id for the transaction
    private Long transactionId;
    //Sender's account id.
    private Long fromAccountId;
    //Receiver's account id.
    private Long toAccountId;
    //Amount to be transferred
    private double amount;
    //Time at which the transaction occurred.
    private LocalDateTime timestamp;
    /**
    Constructor class which represents financial transactions between two accounts,
     It contains information about transaction id, sender's account id, receiver's account id and
     the amount to be transferred.
     */
    public Transaction(Long transactionId, Long fromAccountId, Long toAccountId, double amount){
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
    //Getter method which returns the transaction id.
    public Long getTransactionId() {
        return transactionId;
    }
    //Setter method for transaction id.
    public void setTransactionId(Long transactionId){
        this.transactionId = transactionId;
    }
    //Getter method which return the sender's account id.
    public Long getFromAccountId() {
        return fromAccountId;
    }
    //Setter method for sender's account id.
    public void setFromAccountId(Long fromAccountId){
        this.fromAccountId = fromAccountId;
    }
    //Getter method which return the receiver's account id.
    public Long getToAccountId() {
        return toAccountId;
    }
    //Setter method for receiver's account id.
    public void setToAccountId(Long toAccountId){
        this.toAccountId = toAccountId;
    }
    // Getter for the transaction amount
    public double getAmount() {
        return amount;
    }
    // Setter for the transaction amount
    public void setAmount(double amount){
        this.amount = amount;
    }
    // Getter for the transaction timestamp
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    // Setter for the transaction timestamp
    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }
}