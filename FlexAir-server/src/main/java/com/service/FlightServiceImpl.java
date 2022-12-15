package com.service;

import com.model.entity.Airport;
import com.model.entity.Customer;
import com.model.entity.Flight;
import com.repository.AirportRepository;
import com.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Override
    public List<Flight> getFlights(){
        return  flightRepository.findAll();
    }
    @Override
    public List<String> getCities(){
        List<String> cities = new ArrayList<>();
        List<Airport> airports = airportRepository.findAll();
        for (Airport airport : airports){
            if (!cities.contains(airport.getCityName())){
                cities.add(airport.getCityName());
            }
        }
        return cities;
    }
    @Override
    public Flight getFlightByID(Integer id){
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + id));
        return flight;
    }

}
