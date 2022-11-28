package com.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "passportdata")
public class PassportData {
    @Id
    @Column(name="passportid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int passportId;
    private boolean sex;
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
    public int getPassportId() {
        return passportId;
    }
    public boolean getSex() {
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
}
