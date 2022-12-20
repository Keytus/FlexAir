package com.service;

import com.model.dto.FlightDTO;
import com.model.entity.Airport;
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
    @Override
    public void deleteFlightByID(Integer id){
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + id));
        flightRepository.delete(flight);
    }
    @Override
    public Flight createFlight(FlightDTO flightData){
        Flight flight = new Flight();

//        flight.setArrivalTime(flightData.getArrivalTime());
//        flight.setDepartureTime(flightData.getDepartureTime());
//        flight.setEconomyCost(flightData.getEconomyCost());
//        flight.setFirstClassCost(flightData.getFirstClassCost());
//        flight.setLuxCost(flightData.getLuxCost());
//
//        SeatSuite seatSuite = new SeatSuite();
//        seatSuite.setEconomyReserved(0);
//        seatSuite.setFirstClassReserved(0);
//        seatSuite.setLuxReserved(0);
//        seatSuite.setEconomyTotal(flightData.getEconomyTotal());
//        seatSuite.setFirstClassTotal(flightData.getFirstClassTotal());
//        seatSuite.setLuxTotal(flightData.getLuxTotal());

        return flightRepository.save(flight);
    }
}
