package com.example.banking_transactions.api.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    // Id for the account
    private Long id;
    //Name of the account holder.
    private String name;
    //Current balance of the account.
    private double balance;
    //List to keep track of all the payments.
    private List<Transaction> transactions;
    //Constructor class for id, name and balance.

    public Account(Long id, String name, double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }
    //Getter method, returns account ID.
    public Long getId(){
        return id;
    }
    //Setter method, sets id of the account
    public void setId(Long id){
        this.id = id;
    }
    //Getter method for the balance, returns balance.
    public double getBalance() {
        return balance;
    }
    //Getter method, gets list of all transactions to account, returns list of all transactions.
    public List<Transaction> getTransactions(){
        return transactions;
    }
    //Add a new transaction to the transaction history
    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
    //Updates the balance by adding the amount received and subtracting the amount given.
    public void updateBalance(double amount) {
        this.balance += amount;
    }
}
