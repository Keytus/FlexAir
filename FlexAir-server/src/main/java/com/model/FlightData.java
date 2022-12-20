package com.model;

import java.sql.Timestamp;

public class FlightData {
    private Integer flightID;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private Integer trackID;
    private Integer seatSuiteID;
    private Float economyCost;
    private Float firstClassCost;
    private Float luxCost;

    public FlightData(Integer flightID) {
        this.flightID = flightID;
    }

    public FlightData(Integer flightID, Timestamp arrivalTime, Timestamp departureTime, Integer trackID, Integer seatSuiteID, Float economyCost, Float firstClassCost, Float luxCost) {
        this.flightID = flightID;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.trackID = trackID;
        this.seatSuiteID = seatSuiteID;
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

    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public Integer getSeatSuiteID() {
        return seatSuiteID;
    }

    public void setSeatSuiteID(Integer seatSuiteID) {
        this.seatSuiteID = seatSuiteID;
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
