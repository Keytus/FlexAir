package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @Column(name="trackid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trackID;
    @ManyToOne
    @JoinColumn(name="startairport", referencedColumnName="airportname")
    private Airport startAirport;
    @ManyToOne
    @JoinColumn(name="endairport", referencedColumnName="airportname")
    private Airport endAirport;
    @Column(name="isinwheel")
    private boolean IsInWheel;

    public int getTrackID() {
        return trackID;
    }

    public Airport getStartAirport() {
        return startAirport;
    }

    public Airport getEndAirport() {
        return endAirport;
    }

    public boolean getIsInWheel() {
        return IsInWheel;
    }
}
