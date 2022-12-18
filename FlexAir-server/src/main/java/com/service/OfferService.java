package com.service;

import com.model.entity.Offer;

import java.util.List;

public interface OfferService {
    public List<Offer> getOffers();
    public Offer getOfferByID(Integer id);
}
