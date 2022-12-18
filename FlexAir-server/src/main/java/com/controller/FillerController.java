package com.controller;

import com.model.Message;
import com.service.FillerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/fill")
public class FillerController {
    private FillerService fillerService;

    public FillerController(FillerService fillerService) {
        this.fillerService = fillerService;
    }

    @GetMapping("/news")
    public ResponseEntity<Message> generateNews(@RequestParam String keyWord, @RequestParam Integer generateCount){
        fillerService.generateNews(keyWord, generateCount);
        return new ResponseEntity<>(new Message("success", null), HttpStatus.OK);
    }
}
