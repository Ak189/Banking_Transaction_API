package com.example.banking_transactions.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data transfer object class, Used to transfer data between client and server
 */

public class AccountDTO {
    //Profile first name, can't be null.
    @NotNull(message = "First name cannot be null")
    private String firstName;
    //Profile middle name, (optional)
    private String middleName;
    //Profile last name, can't be null.
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    //Profile address line 1, can't be null.
    @NotNull(message = "Address line 1 cannot be null")
    private String addressLine1;
    //Profile address line 2, (optional).
    private String addressLine2;
    //Profile city, required.
    @NotNull(message = "City cannot be null")
    private String city;
    //Profile's State, can't be null.
    @NotNull(message = "State cannot be null")
    private String state;
    //Profile's postal code, required.
    @NotNull(message = "Postal code cannot be null")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    private String postalCode;
    //Profile's driving license number, required and can't be duplicate.
    @NotNull(message = "Driving license cannot be null")
    private String drivingLicense;
    //Profile's SIN number, required and can't be duplicate.
    @NotNull(message = "SIN number cannot be null")
    @Size(min = 9, max = 9, message = "SIN number must be 9 digits")
    private String sinNumber;
    //Initial balance
    private double initialBalance;
    //Constructor class to create a new AccountDTO instance with all of the above as parameters.
    public AccountDTO(String firstName, String middleName, String lastName, String addressLine1, String addressLine2,
                      String city, String state, String postalCode, String drivingLicense, String sinNumber, double initialBalance) {
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
        this.initialBalance = initialBalance;
    }
    //Getters and Setters method for all of the parameters.

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }

    public double getInitialBalance() {
        return initialBalance;
    }
    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }
}

