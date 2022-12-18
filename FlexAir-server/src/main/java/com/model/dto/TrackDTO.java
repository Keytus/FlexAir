package com.model.dto;

public class TrackDTO {
    private Integer trackID;
    private String arrivalAirportName;
    private String arrivalCityName;
    private String arrivalCountryCode;
    private String departureAirportName;
    private String departureCityName;
    private String departureCountryCode;

    public TrackDTO(Integer trackID, String arrivalAirportName, String arrivalCityName,
                    String arrivalCountryCode, String departureAirportName, String departureCityName,
                    String departureCountryCode) {
        this.trackID = trackID;
        this.arrivalAirportName = arrivalAirportName;
        this.arrivalCityName = arrivalCityName;
        this.arrivalCountryCode = arrivalCountryCode;
        this.departureAirportName = departureAirportName;
        this.departureCityName = departureCityName;
        this.departureCountryCode = departureCountryCode;
    }


    public Integer getTrackID() {
        return trackID;
    }

    public void setTrackID(Integer trackID) {
        this.trackID = trackID;
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
}
