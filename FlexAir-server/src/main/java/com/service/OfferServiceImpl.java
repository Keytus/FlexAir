package com.service;

import com.model.entity.Offer;
import com.repository.OfferRepository;
import com.repository.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    private PromocodeRepository promocodeRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Override
    public List<Offer> getOffers(){
        return offerRepository.findAll();
    }
    @Override
    public Offer getOfferByID(Integer id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not exist with id :" + id));
        return offer;
    }
}
