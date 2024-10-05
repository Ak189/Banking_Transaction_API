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
    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String postalCode;
    private String Country;
    public Customer(String firstName, String middleName, String lastName, Long SinNo, Long DlNo, String AddressLine1, String AddressLine2,
                    String City, String State, String postalCode, String Country){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.SinNo = SinNo;
        this.DlNo = DlNo;
        this.AddressLine1 = AddressLine1;
        this.AddressLine2 = AddressLine2;
        this.City = City;
        this.State = State;
        this.postalCode = postalCode;
        this.Country = Country;
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

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

}
