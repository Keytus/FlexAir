package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name="customerid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;
    private String login;
    @ManyToOne
    @JoinColumn(name = "passportid")
    private PassportData passportData;
    private String email;
    @Column(name="customertype")
    private String customerType;
    @Column(name="customerpassword")
    private String customerPassword;
    public int getCustomerID() {
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
}
