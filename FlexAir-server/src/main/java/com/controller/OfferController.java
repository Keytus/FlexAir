package com.controller;

import com.model.Message;
import com.model.dto.ExtendedOfferDTO;
import com.model.dto.OfferDTO;
import com.model.entity.Offer;
import com.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteOfferByID(@PathVariable Integer id){
        offerService.deleteOfferByID(id);
        return new ResponseEntity<>(new Message("success", id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateOfferByID(@PathVariable Integer id, @RequestBody ExtendedOfferDTO offerData){
        Offer offer = offerService.updateOfferByID(id, offerData);
        return new ResponseEntity<>(new Message("success", offer.getOfferID()), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> createOffer(@RequestBody ExtendedOfferDTO offerData){
        Offer offer = offerService.createOffer(offerData);
        return new ResponseEntity<>(new Message("success", offer.getOfferID()), HttpStatus.OK);
    }
    @GetMapping("/dto/offers")
    public List<OfferDTO> getOfferDTOs(){
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

    @GetMapping("/extended_dto/offers")
    public List<ExtendedOfferDTO> getExtendedOfferDTOs(){
        List<ExtendedOfferDTO> offerExtendedDTOList = new ArrayList<>();
        List<Offer> offerList = offerService.getOffers();
        for(Offer offer : offerList){
            offerExtendedDTOList.add(convertExtendedOfferToDTO(offer));
        }
        return offerExtendedDTOList;
    }

    @GetMapping("/extended_dto/{id}")
    public ExtendedOfferDTO getExtendedOfferDTOByID(@PathVariable Integer id){
        Offer offer = offerService.getOfferByID(id);
        ExtendedOfferDTO offerExtendedDTO = convertExtendedOfferToDTO(offer);
        return offerExtendedDTO;
    }

    private OfferDTO convertOfferToDTO(Offer offer){
        return new OfferDTO(offer.getOfferID(), offer.getPromocode().getPromocodeValue(),
                offer.getInfoBlock().getHeader(), offer.getInfoBlock().getMain());
    }

    private ExtendedOfferDTO convertExtendedOfferToDTO(Offer offer){
        return new ExtendedOfferDTO(offer.getOfferID(), offer.getPromocode().getPromocodeValue(),
                offer.getPromocode().getDiscount(), offer.getPromocode().getUseCount(),
                offer.getPromocode().getTrack().getTrackID(), offer.getInfoBlock().getHeader(),
                offer.getInfoBlock().getMain());
    }
}
