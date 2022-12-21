package com.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column(name="isactive", columnDefinition = "boolean default true")
    private Boolean isActive;
    @Column(name="isadmin", columnDefinition = "boolean default false")
    private Boolean isAdmin;
    @Column(name="last_login")
    private Timestamp lastLogin;
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

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
