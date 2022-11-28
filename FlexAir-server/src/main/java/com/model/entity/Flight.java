package com.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name="flightid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int flightID;
    @Column(name="arrivaltime")
    private Timestamp arrivalTime;
    @Column(name="departuretime")
    private Timestamp departureTime;
    @ManyToOne
    @JoinColumn(name="seatsuiteid")
    private SeatSuite seatSuite;
    @ManyToOne
    @JoinColumn(name="trackid")
    private Track track;

    public int getFlightID() {
        return flightID;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public SeatSuite getSeatSuite() {
        return seatSuite;
    }

    public Track getTrack() {
        return track;
    }
}
