package com.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name="airportname")
    private String airportName;
    @Column(name="cityname")
    private String cityName;
    @Column(name="countrycode")
    private String countryCode;

    public String getAirportName() {
        return airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
