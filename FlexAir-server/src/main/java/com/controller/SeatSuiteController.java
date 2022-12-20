package com.controller;

import com.model.Message;
import com.model.entity.SeatSuite;
import com.service.SeatSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/seatsuite")
public class SeatSuiteController {
    @Autowired
    private SeatSuiteService seatSuiteService;

    @GetMapping("/seatsuites")
    public List<SeatSuite> getSeatSuites(){
        return seatSuiteService.getSeatSuites();
    }
    @GetMapping("/{id}")
    public SeatSuite getSeatSuiteByID(@PathVariable Integer id){
        return seatSuiteService.getSeatSuiteByID(id);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> createSeatSuite(@RequestBody SeatSuite seatSuiteData){
        SeatSuite seatSuite = seatSuiteService.createSeatSuite(seatSuiteData);
        return new ResponseEntity<>(new Message("success", seatSuite.getSeatSuiteID()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteSeatSuiteByID(@PathVariable Integer id){
        seatSuiteService.deleteSeatSuiteByID(id);
        return new ResponseEntity<>(new Message("success", id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateSeatSuiteByID(@PathVariable Integer id, @RequestBody SeatSuite seatSuiteData){
        SeatSuite seatSuite = seatSuiteService.updateSeatSuiteByID(id, seatSuiteData);
        return new ResponseEntity<>(new Message("success", seatSuite.getSeatSuiteID()), HttpStatus.OK);
    }
}
