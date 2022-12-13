package com.controller;

import com.model.entity.News;
import com.model.entity.Offer;
import com.service.OfferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/offer")
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        super();
        this.offerService = offerService;
    }
    @GetMapping("/offers")
    public List<Offer> getOffers(){
        return offerService.getOffers();
    }
    @GetMapping("/{id}")
    public Offer getOfferByID(@PathVariable Integer id){
        return offerService.getOfferByID(id);
    }
}
