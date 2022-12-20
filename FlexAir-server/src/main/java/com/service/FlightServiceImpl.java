package com.service;

import com.model.FlightData;
import com.model.entity.Airport;
import com.model.entity.Flight;
import com.model.entity.SeatSuite;
import com.model.entity.Track;
import com.repository.AirportRepository;
import com.repository.FlightRepository;
import com.repository.SeatSuiteRepository;
import com.repository.TrackRepository;
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
    @Autowired
    private SeatSuiteRepository seatSuiteRepository;
    @Autowired
    private TrackRepository trackRepository;
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
    public Flight createFlight(FlightData flightData){
        Flight flight = new Flight();

        flight.setArrivalTime(flightData.getArrivalTime());
        flight.setDepartureTime(flightData.getDepartureTime());
        flight.setEconomyCost(flightData.getEconomyCost());
        flight.setFirstClassCost(flightData.getFirstClassCost());
        flight.setLuxCost(flightData.getLuxCost());

        SeatSuite seatSuite = seatSuiteRepository.findById(flightData.getSeatSuiteID())
                .orElseThrow(() -> new ResourceNotFoundException("SeatSuite not exist with id :" + flightData.getSeatSuiteID()));
        flight.setSeatSuite(seatSuite);
        Track track = trackRepository.findById(flightData.getTrackID())
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + flightData.getTrackID()));
        flight.setTrack(track);

        return flightRepository.save(flight);
    }

    public Flight updateFlightByID(Integer id, FlightData flightData){
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + id));

        flight.setArrivalTime(flightData.getArrivalTime());
        flight.setDepartureTime(flightData.getDepartureTime());
        flight.setEconomyCost(flightData.getEconomyCost());
        flight.setFirstClassCost(flightData.getFirstClassCost());
        flight.setLuxCost(flightData.getLuxCost());

        SeatSuite seatSuite = seatSuiteRepository.findById(flightData.getSeatSuiteID())
                .orElseThrow(() -> new ResourceNotFoundException("SeatSuite not exist with id :" + flightData.getSeatSuiteID()));
        flight.setSeatSuite(seatSuite);
        Track track = trackRepository.findById(flightData.getTrackID())
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + flightData.getTrackID()));
        flight.setTrack(track);

        return flightRepository.save(flight);
    }
}
