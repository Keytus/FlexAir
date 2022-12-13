package com.service;

import com.model.entity.News;
import com.model.entity.Offer;
import com.repository.InfoBlockRepository;
import com.repository.OfferRepository;
import com.repository.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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
    @Override
    public String generatePromocodeValue(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer;
        do {
            buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
        } while (!promocodeRepository.findByPromocodeValue(buffer.toString()).isEmpty());

        return buffer.toString();
    }
}
