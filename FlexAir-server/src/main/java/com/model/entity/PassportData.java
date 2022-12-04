package com.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "passportdata")
public class PassportData {
    @Id
    @Column(name="passportid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer passportId;
    private Boolean sex;
    @Column(name="givenname")
    private String givenName;
    private String surname;
    @Column(name="dateofbirth")
    private Date dateOfBirth;
    @Column(name="dateofissue")
    private Date dateOfIssue;
    @Column(name="dateofexpery")
    private Date dateOfExpery;
    @Column(name="idnumber")
    private String idNumber;
    private String country;
    private String authority;
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public Integer getPassportId() {
        return passportId;
    }
    public Boolean getSex() {
        return sex;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public Date getDateOfExpery() {
        return dateOfExpery;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getAuthority() {
        return authority;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setDateOfExpery(Date dateOfExpery) {
        this.dateOfExpery = dateOfExpery;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "PassportData[" +
                "passportId=" + passportId +
                ", sex=" + sex +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpery=" + dateOfExpery +
                ", idNumber='" + idNumber + '\'' +
                ", country='" + country + '\'' +
                ", authority='" + authority + '\'' +
                ']';
    }
}
