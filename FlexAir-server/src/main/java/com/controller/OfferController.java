package com.controller;

import com.model.dto.OfferDTO;
import com.model.entity.Offer;
import com.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/offer")
public class OfferController {
    @Autowired
    private OfferService offerService;
    @GetMapping("/offers")
    public List<Offer> getOffers(){
        return offerService.getOffers();
    }
    @GetMapping("/{id}")
    public Offer getOfferByID(@PathVariable Integer id){
        return offerService.getOfferByID(id);
    }
    @GetMapping("/dto/offers")
    public List<OfferDTO> getOfferDTO(){
        List<OfferDTO> offerDTOList = new ArrayList<>();
        List<Offer> offerList = offerService.getOffers();
        for(Offer offer : offerList){
            offerDTOList.add(convertOfferToDTO(offer));
        }
        return offerDTOList;
    }

    @GetMapping("/dto/{id}")
    public OfferDTO getOfferDTOByID(@PathVariable Integer id){
        Offer offer = offerService.getOfferByID(id);
        OfferDTO offerDTO = convertOfferToDTO(offer);
        return offerDTO;
    }

    private OfferDTO convertOfferToDTO(Offer offer){
        return new OfferDTO(offer.getOfferID(), offer.getPromocode().getPromocodeValue(),
                offer.getInfoBlock().getHeader(), offer.getInfoBlock().getMain());
    }
}
