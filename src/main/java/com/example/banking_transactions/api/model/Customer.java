package com.example.banking_transactions.api.model;


public class Customer {
    //Defining first name.
    private String firstName;
    //Defining middle name.
    private String middleName;
    //Defining last name.
    private String lastName;
    //Defining Sin Number.
    private Long SinNo;
    //Defining driving license number.
    private Long DlNo;
    //Constructor class for customer.
    public Customer(String firstName, String middleName, String lastName, Long SinNo, Long DlNo){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.SinNo = SinNo;
        this.DlNo = DlNo;
    }
    //Getter method for first name.
    public String getFirstName() {
        return firstName;
    }
    //Setter method for first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    //Getter method for middle name.
    public String getMiddleName() {
        return middleName;
    }
    //Setter method for middle name
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    //Getter method for last name
    public String getLastName() {
        return lastName;
    }
    //Setter method for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Getter method for SIN number
    public Long getSinNo() {
        return SinNo;
    }
    //Setter method for SIN Number
    public void setSinNo(Long sinNo) {
        SinNo = sinNo;
    }
    //Getter method for Driving license number
    public Long getDlNo() {
        return DlNo;
    }
    //Setter method for driving license number
    public void setDlNo(Long dlNo) {
        DlNo = dlNo;
    }
}
