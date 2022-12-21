package com.controller;

import com.model.Message;
import com.model.dto.NewsDTO;
import com.model.dto.TrackDTO;
import com.model.entity.Track;
import com.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/track")
public class TrackController {
    @Autowired
    private TrackService trackService;
    @GetMapping("/tracks")
    public List<Track> getTracks(){
        return trackService.getTracks();
    }
    @GetMapping("/{id}")
    public Track getTrackByID(@PathVariable Integer id){
        return trackService.getTrackByID(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteTrackBuID(@PathVariable Integer id){
        trackService.getTrackByID(id);
        return new ResponseEntity<>(new Message("success", id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateTrackByID(@PathVariable Integer id, @RequestBody TrackDTO trackData){
        Track track = trackService.updateTrackByID(id, trackData);
        return new ResponseEntity<>(new Message("success", track.getTrackID()), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> createTrack(@RequestBody TrackDTO trackData){
        Track track = trackService.createTrack(trackData);
        return new ResponseEntity<>(new Message("success", track.getTrackID()), HttpStatus.OK);
    }

}
