package com.example.banking_transactions.api.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long id;
    private String name;
    private double balance;
    private List<Transaction> transactions;

    public Account(Long id, String name, double balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance() {
        this.balance = balance;
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }
}
