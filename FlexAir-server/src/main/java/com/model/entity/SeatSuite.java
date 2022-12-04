package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "seatsuite")
public class SeatSuite {
    @Id
    @Column(name="seatsuiteid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer seatSuiteID;
    @Column(name="economytotal")
    private Integer economyTotal;
    @Column(name="firstclasstotal")
    private Integer firstClassTotal;
    @Column(name="luxtotal")
    private Integer luxTotal;
    @Column(name="economyreserved")
    private Integer economyReserved;
    @Column(name="firstclassreserved")
    private Integer firstClassReserved;
    @Column(name="luxreserved")
    private Integer luxReserved;

    public Integer getSeatSuiteID() {
        return seatSuiteID;
    }

    public Integer getEconomyTotal() {
        return economyTotal;
    }

    public Integer getFirstClassTotal() {
        return firstClassTotal;
    }

    public Integer getLuxTotal() {
        return luxTotal;
    }

    public Integer getEconomyReserved() {
        return economyReserved;
    }

    public Integer getFirstClassReserved() {
        return firstClassReserved;
    }

    public Integer getLuxReserved() {
        return luxReserved;
    }

    public void setSeatSuiteID(Integer seatSuiteID) {
        this.seatSuiteID = seatSuiteID;
    }

    public void setEconomyTotal(Integer economyTotal) {
        this.economyTotal = economyTotal;
    }

    public void setFirstClassTotal(Integer firstClassTotal) {
        this.firstClassTotal = firstClassTotal;
    }

    public void setLuxTotal(Integer luxTotal) {
        this.luxTotal = luxTotal;
    }

    public void setEconomyReserved(Integer economyReserved) {
        this.economyReserved = economyReserved;
    }

    public void setFirstClassReserved(Integer firstClassReserved) {
        this.firstClassReserved = firstClassReserved;
    }

    public void setLuxReserved(Integer luxReserved) {
        this.luxReserved = luxReserved;
    }

    @Override
    public String toString() {
        return "SeatSuite[" +
                "seatSuiteID=" + seatSuiteID +
                ", economyTotal=" + economyTotal +
                ", firstClassTotal=" + firstClassTotal +
                ", luxTotal=" + luxTotal +
                ", economyReserved=" + economyReserved +
                ", firstClassReserved=" + firstClassReserved +
                ", luxReserved=" + luxReserved +
                ']';
    }
}
