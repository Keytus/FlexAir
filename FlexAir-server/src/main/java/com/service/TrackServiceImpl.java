package com.service;

import com.model.dto.TrackDTO;
import com.model.entity.Airport;
import com.model.entity.Track;
import com.repository.AirportRepository;
import com.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Override
    public List<Track> getTracks(){
        return trackRepository.findAll();
    }
    @Override
    public Track getTrackByID(Integer id){
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + id));
        return track;
    }
    @Override
    public void deleteTrackByID(Integer id){
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + id));
        trackRepository.delete(track);
    }
    @Override
    public Track createTrack(TrackDTO trackData){
        Track track = new Track();
        track.setInWheel(false);

        List<Airport> arrivalAirports = airportRepository.findByAirportName(trackData.getArrivalAirportName());
        if (arrivalAirports.isEmpty()){
            Airport arrivalAirport = new Airport();
            arrivalAirport.setAirportName(trackData.getArrivalAirportName());
            arrivalAirport.setCityName(trackData.getArrivalCityName());
            arrivalAirport.setCountryCode(trackData.getArrivalCountryCode());
            airportRepository.save(arrivalAirport);
            track.setEndAirport(arrivalAirport);
        }
        else track.setEndAirport(arrivalAirports.get(0));

        List<Airport> departureAirports = airportRepository.findByAirportName(trackData.getDepartureAirportName());
        if (departureAirports.isEmpty()){
            Airport departureAirport = new Airport();
            departureAirport.setAirportName(trackData.getDepartureAirportName());
            departureAirport.setCityName(trackData.getDepartureCityName());
            departureAirport.setCountryCode(trackData.getDepartureCountryCode());
            airportRepository.save(departureAirport);
            track.setStartAirport(departureAirport);
        }
        else track.setStartAirport(departureAirports.get(0));

        return trackRepository.save(track);
    }
    @Override
    public Track updateTrackByID(Integer id, TrackDTO trackData){
        Track track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + id));

        List<Airport> arrivalAirports = airportRepository.findByAirportName(trackData.getArrivalAirportName());
        if (arrivalAirports.isEmpty()){
            Airport arrivalAirport = new Airport();
            arrivalAirport.setAirportName(trackData.getArrivalAirportName());
            arrivalAirport.setCityName(trackData.getArrivalCityName());
            arrivalAirport.setCountryCode(trackData.getArrivalCountryCode());
            airportRepository.save(arrivalAirport);
            track.setEndAirport(arrivalAirport);
        }
        else track.setEndAirport(arrivalAirports.get(0));

        List<Airport> departureAirports = airportRepository.findByAirportName(trackData.getDepartureAirportName());
        if (departureAirports.isEmpty()){
            Airport departureAirport = new Airport();
            departureAirport.setAirportName(trackData.getDepartureAirportName());
            departureAirport.setCityName(trackData.getDepartureCityName());
            departureAirport.setCountryCode(trackData.getDepartureCountryCode());
            airportRepository.save(departureAirport);
            track.setStartAirport(departureAirport);
        }
        else track.setStartAirport(departureAirports.get(0));

        return trackRepository.save(track);
    }
}
