package com.controller;

import com.model.dto.OfferDTO;
import com.model.dto.TrackDTO;
import com.model.entity.Offer;
import com.model.entity.Track;
import com.service.WheelOfFortuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fortune")
public class WheelOfFortuneController {
    @Autowired
    private WheelOfFortuneService wheelOfFortuneService;
    @GetMapping("/tracks")
    public List<TrackDTO> getFortuneTracks(){
        List<TrackDTO> trackDTOs = new ArrayList<>();
        List<Track> tracks = wheelOfFortuneService.getFortuneTracks();
        for (Track track : tracks){
            trackDTOs.add(convertTrackToDTO(track));
        }
        return trackDTOs;
    }
    @GetMapping("/generate_promovalue")
    private String generatePromoValue(){
        return wheelOfFortuneService.generatePromocodeValue();
    }

    private TrackDTO convertTrackToDTO(Track track){
        return new TrackDTO(track.getTrackID(), track.getStartAirport().getAirportName(),
                track.getStartAirport().getCityName(), track.getStartAirport().getCountryCode(),
                track.getEndAirport().getAirportName(), track.getEndAirport().getCityName(),
                track.getEndAirport().getCountryCode());
    }
}
