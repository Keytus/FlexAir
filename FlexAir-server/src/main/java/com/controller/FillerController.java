package com.controller;

import com.model.Message;
import com.service.FillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fill")
public class FillerController {
    @Autowired
    private FillerService fillerService;

    @GetMapping("/news")
    public ResponseEntity<Message> generateNews(){
        fillerService.generateNews();
        return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
    }
    @GetMapping("/flights")
    public ResponseEntity<Message> generateFlights(@RequestParam Integer generateCount){
        fillerService.generateFlights(generateCount);
        return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
    }
}
