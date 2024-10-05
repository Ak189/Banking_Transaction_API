package com.example.banking_transactions.api.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    // Unique ID for the account
    private Long id;

    // Personal details
    private String firstName;
    private String middleName;
    private String lastName;

    // Address details
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;

    // Identification details
    private String drivingLicense;
    private String sinNumber;  // Social Insurance Number

    // Account-specific information
    private double balance;
    private List<Transaction> transactions;

    // Constructor class for all fields
    public Account(Long id, String firstName, String middleName, String lastName, String addressLine1,
                   String addressLine2, String city, String state, String postalCode, String drivingLicense,
                   String sinNumber, double balance) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.drivingLicense = drivingLicense;
        this.sinNumber = sinNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    // Getter and Setter methods for ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for first name
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter methods for middle name
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // Getter and Setter methods for last name
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter methods for address line 1
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    // Getter and Setter methods for address line 2
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    // Getter and Setter methods for city
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter methods for state
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    // Getter and Setter methods for postal code
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // Getter and Setter methods for driving license
    public String getDrivingLicense() {
        return drivingLicense;
    }
    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    // Getter and Setter methods for SIN number
    public String getSinNumber() {
        return sinNumber;
    }
    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }

    // Getter and Setter methods for balance
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to get the list of all transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Method to add a new transaction to the account
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // Method to update the balance (positive for deposits, negative for withdrawals)
    public void updateBalance(double amount) {
        this.balance += amount;
    }

    // Override equals() and hashCode() methods to handle duplicates based on driving license and SIN number
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return drivingLicense.equals(account.drivingLicense) || sinNumber.equals(account.sinNumber);
    }

    @Override
    public int hashCode() {
        int result = drivingLicense.hashCode();
        result = 31 * result + sinNumber.hashCode();
        return result;
    }
}

