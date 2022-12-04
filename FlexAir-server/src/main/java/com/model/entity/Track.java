package com.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @Column(name="trackid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer trackID;
    @ManyToOne
    @JoinColumn(name="startairport", referencedColumnName="airportname")
    private Airport startAirport;
    @ManyToOne
    @JoinColumn(name="endairport", referencedColumnName="airportname")
    private Airport endAirport;
    @Column(name="isinwheel")
    private Boolean IsInWheel;

    public Integer getTrackID() {
        return trackID;
    }

    public Airport getStartAirport() {
        return startAirport;
    }

    public Airport getEndAirport() {
        return endAirport;
    }

    public Boolean getIsInWheel() {
        return IsInWheel;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
    }

    public void setStartAirport(Airport startAirport) {
        this.startAirport = startAirport;
    }

    public void setEndAirport(Airport endAirport) {
        this.endAirport = endAirport;
    }

    public void setInWheel(Boolean inWheel) {
        IsInWheel = inWheel;
    }

    @Override
    public String toString() {
        return "Track[" +
                "trackID=" + trackID +
                ", startAirport=" + startAirport +
                ", endAirport=" + endAirport +
                ", IsInWheel=" + IsInWheel +
                ']';
    }
}
