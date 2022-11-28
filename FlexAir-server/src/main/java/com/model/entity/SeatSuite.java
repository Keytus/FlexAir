package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "seatsuite")
public class SeatSuite {
    @Id
    @Column(name="seatsuiteid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seatSuiteID;
    @Column(name="economytotal")
    private int economyTotal;
    @Column(name="firstclasstotal")
    private int firstClassTotal;
    @Column(name="luxtotal")
    private int luxTotal;
    @Column(name="economyreserved")
    private int economyReserved;
    @Column(name="firstclassreserved")
    private int firstClassReserved;
    @Column(name="luxreserved")
    private int luxReserved;

    public int getSeatSuiteID() {
        return seatSuiteID;
    }

    public int getEconomyTotal() {
        return economyTotal;
    }

    public int getFirstClassTotal() {
        return firstClassTotal;
    }

    public int getLuxTotal() {
        return luxTotal;
    }

    public int getEconomyReserved() {
        return economyReserved;
    }

    public int getFirstClassReserved() {
        return firstClassReserved;
    }

    public int getLuxReserved() {
        return luxReserved;
    }
}
