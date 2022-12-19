package com.service;

import com.model.dto.ExtendedOfferDTO;
import com.model.entity.InfoBlock;
import com.model.entity.Offer;
import com.model.entity.Promocode;
import com.model.entity.Track;
import com.repository.InfoBlockRepository;
import com.repository.OfferRepository;
import com.repository.PromocodeRepository;
import com.repository.TrackRepository;
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
    @Autowired
    private InfoBlockRepository infoBlockRepository;

    @Autowired
    private TrackRepository trackRepository;
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
    public void deleteOfferByID(Integer id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not exist with id :" + id));
        offerRepository.delete(offer);
        promocodeRepository.delete(offer.getPromocode());
        infoBlockRepository.delete(offer.getInfoBlock());
    }
    @Override
    public Offer updateOfferByID(Integer id, ExtendedOfferDTO offerData){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not exist with id :" + id));
        InfoBlock infoBlock = offer.getInfoBlock();
        infoBlock.setMain(offerData.getMain());
        infoBlock.setHeader(offerData.getHeader());

        Promocode promocode = offer.getPromocode();
        promocode.setPromocodeValue(offerData.getPromocodeValue());
        promocode.setDiscount(offerData.getDiscount());
        promocode.setUseCount(offerData.getUseCount());

        Track track = trackRepository.findById(offerData.getTrackID())
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + offerData.getTrackID()));
        promocode.setTrack(track);

        promocodeRepository.save(promocode);
        infoBlockRepository.save(infoBlock);
        return offerRepository.save(offer);
    }
    @Override
    public Offer createOffer(ExtendedOfferDTO offerData){
        Offer offer = new Offer();

        InfoBlock infoBlock = new InfoBlock();
        infoBlock.setMain(offerData.getMain());
        infoBlock.setHeader(offerData.getHeader());
        infoBlockRepository.save(infoBlock);
        offer.setInfoBlock(infoBlock);

        Promocode promocode = new Promocode();
        promocode.setPromocodeValue(offerData.getPromocodeValue());
        promocode.setDiscount(offerData.getDiscount());
        promocode.setUseCount(offerData.getUseCount());

        Track track = trackRepository.findById(offerData.getTrackID())
                .orElseThrow(() -> new ResourceNotFoundException("Track not exist with id :" + offerData.getTrackID()));
        promocode.setTrack(track);
        promocodeRepository.save(promocode);
        offer.setPromocode(promocode);

        return offerRepository.save(offer);
    }
}
