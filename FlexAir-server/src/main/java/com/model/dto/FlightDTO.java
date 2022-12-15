package com.model.dto;

import java.sql.Timestamp;

public class FlightDTO {
    private Integer flightID;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private String arrivalAirportName;
    private String arrivalCityName;
    private String arrivalCountryCode;
    private String departureAirportName;
    private String departureCityName;
    private String departureCountryCode;
    private Integer economyTotal;
    private Integer firstClassTotal;
    private Integer luxTotal;
    private Integer economyReserved;
    private Integer firstClassReserved;
    private Integer luxReserved;
    private Float economyCost;
    private Float firstClassCost;
    private Float luxCost;

    public FlightDTO(Integer flightID, Timestamp arrivalTime, Timestamp departureTime,
                     String arrivalAirportName, String arrivalCityName, String arrivalCountryCode,
                     String departureAirportName, String departureCityName, String departureCountryCode,
                     Integer economyTotal, Integer firstClassTotal, Integer luxTotal, Integer economyReserved,
                     Integer firstClassReserved, Integer luxReserved, Float economyCost, Float firstClassCost,
                     Float luxCost) {
        this.flightID = flightID;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalCityName = arrivalCityName;
        this.arrivalCountryCode = arrivalCountryCode;
        this.departureAirportName = departureAirportName;
        this.departureCityName = departureCityName;
        this.departureCountryCode = departureCountryCode;
        this.economyTotal = economyTotal;
        this.firstClassTotal = firstClassTotal;
        this.luxTotal = luxTotal;
        this.economyReserved = economyReserved;
        this.firstClassReserved = firstClassReserved;
        this.luxReserved = luxReserved;
        this.economyCost = economyCost;
        this.firstClassCost = firstClassCost;
        this.luxCost = luxCost;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public void setArrivalCityName(String arrivalCityName) {
        this.arrivalCityName = arrivalCityName;
    }

    public String getArrivalCountryCode() {
        return arrivalCountryCode;
    }

    public void setArrivalCountryCode(String arrivalCountryCode) {
        this.arrivalCountryCode = arrivalCountryCode;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getDepartureCountryCode() {
        return departureCountryCode;
    }

    public void setDepartureCountryCode(String departureCountryCode) {
        this.departureCountryCode = departureCountryCode;
    }

    public Integer getEconomyTotal() {
        return economyTotal;
    }

    public void setEconomyTotal(Integer economyTotal) {
        this.economyTotal = economyTotal;
    }

    public Integer getFirstClassTotal() {
        return firstClassTotal;
    }

    public void setFirstClassTotal(Integer firstClassTotal) {
        this.firstClassTotal = firstClassTotal;
    }

    public Integer getLuxTotal() {
        return luxTotal;
    }

    public void setLuxTotal(Integer luxTotal) {
        this.luxTotal = luxTotal;
    }

    public Integer getEconomyReserved() {
        return economyReserved;
    }

    public void setEconomyReserved(Integer economyReserved) {
        this.economyReserved = economyReserved;
    }

    public Integer getFirstClassReserved() {
        return firstClassReserved;
    }

    public void setFirstClassReserved(Integer firstClassReserved) {
        this.firstClassReserved = firstClassReserved;
    }

    public Integer getLuxReserved() {
        return luxReserved;
    }

    public void setLuxReserved(Integer luxReserved) {
        this.luxReserved = luxReserved;
    }

    public Float getEconomyCost() {
        return economyCost;
    }

    public void setEconomyCost(Float economyCost) {
        this.economyCost = economyCost;
    }

    public Float getFirstClassCost() {
        return firstClassCost;
    }

    public void setFirstClassCost(Float firstClassCost) {
        this.firstClassCost = firstClassCost;
    }

    public Float getLuxCost() {
        return luxCost;
    }

    public void setLuxCost(Float luxCost) {
        this.luxCost = luxCost;
    }
}
