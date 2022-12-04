package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name="customerid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer customerID;
    private String login;
    @ManyToOne
    @JoinColumn(name = "passportid")
    private PassportData passportData;
    private String email;
    @Column(name="customertype")
    private String customerType;
    @Column(name="customerpassword")
    private String customerPassword;
    public Integer getCustomerID() {
        return customerID;
    }
    public String getLogin() {
        return login;
    }
    public String getCustomerType() {
        return customerType;
    }
    public PassportData getPassportData() {
        return passportData;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    @Override
    public String toString() {
        return "Customer[" +
                "customerID=" + customerID +
                ", login='" + login + '\'' +
                ", passportData=" + passportData +
                ", email='" + email + '\'' +
                ", customerType='" + customerType + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ']';
    }
}
