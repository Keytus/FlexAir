package com.service;

import com.model.entity.Flight;

import java.util.List;

public interface FlightService {
    public List<Flight> getFlights();
    public List<String> getCities();
    public Flight getFlightByID(Integer id);
}
