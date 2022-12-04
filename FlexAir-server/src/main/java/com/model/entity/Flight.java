package com.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name="flightid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer flightID;
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

    public Integer getFlightID() {
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

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setSeatSuite(SeatSuite seatSuite) {
        this.seatSuite = seatSuite;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "Flight[" +
                "flightID=" + flightID +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", seatSuite=" + seatSuite +
                ", track=" + track +
                ']';
    }
}
