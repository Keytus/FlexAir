package com.controller;

import com.model.FlightData;
import com.model.Message;
import com.model.dto.FlightDTO;
import com.model.entity.Flight;
import com.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }
    @GetMapping("/cities")
    public List<String> getCities(){
        return flightService.getCities();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteFlightByID(@PathVariable Integer id){
        flightService.deleteFlightByID(id);
        return new ResponseEntity<>(new Message("success", id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> createFlight(@RequestBody FlightData flightData){
        Flight flight = flightService.createFlight(flightData);
        return new ResponseEntity<>(new Message("success", flight.getFlightID()), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateFlightByID(@PathVariable Integer id, @RequestBody FlightData flightData){
        Flight flight = flightService.updateFlightByID(id, flightData);
        return new ResponseEntity<>(new Message("success", flight.getFlightID()), HttpStatus.OK);
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
