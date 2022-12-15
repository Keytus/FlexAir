package com.controller;

import com.model.dto.FlightDTO;
import com.model.dto.NewsDTO;
import com.model.entity.Flight;
import com.model.entity.News;
import com.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flight")
public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }
    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }
    @GetMapping("/cities")
    public List<String> getCities(){
        return flightService.getCities();
    }

    @GetMapping("/dto/flights")
    public List<FlightDTO> getFlightsDTO(){
        List<FlightDTO> flightsDTOList = new ArrayList<>();
        List<Flight> flightList = flightService.getFlights();
        for(Flight flight : flightList){
            flightsDTOList.add(convertFlightToDTO(flight));
        }
        return flightsDTOList;
    }
    @GetMapping("/dto/{id}")
    public FlightDTO getFlightDTOByID(@PathVariable Integer id){
        Flight flight = flightService.getFlightByID(id);
        FlightDTO flightDTO = convertFlightToDTO(flight);
        return flightDTO;
    }

    private FlightDTO convertFlightToDTO(Flight flight){
        return new FlightDTO(flight.getFlightID(), flight.getArrivalTime(), flight.getDepartureTime(),
                flight.getTrack().getEndAirport().getAirportName(), flight.getTrack().getEndAirport().getCityName(),
                flight.getTrack().getEndAirport().getCountryCode(), flight.getTrack().getStartAirport().getAirportName(),
                flight.getTrack().getStartAirport().getCityName(), flight.getTrack().getStartAirport().getCountryCode(),
                flight.getSeatSuite().getEconomyTotal(), flight.getSeatSuite().getFirstClassTotal(),
                flight.getSeatSuite().getLuxTotal(), flight.getSeatSuite().getEconomyReserved(),
                flight.getSeatSuite().getFirstClassReserved(), flight.getSeatSuite().getLuxReserved(),
                flight.getEconomyCost(), flight.getFirstClassCost(), flight.getLuxCost());
    }
}
