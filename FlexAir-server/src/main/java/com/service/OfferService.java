package com.service;

import com.model.dto.ExtendedOfferDTO;
import com.model.dto.OfferDTO;
import com.model.entity.Offer;
import com.repository.OfferRepository;

import java.util.List;

public interface OfferService {
    public List<Offer> getOffers();
    public Offer getOfferByID(Integer id);
    public void deleteOfferByID(Integer id);
    public Offer updateOfferByID(Integer id, ExtendedOfferDTO offerData);
    public Offer createOffer(ExtendedOfferDTO offerData);
}
