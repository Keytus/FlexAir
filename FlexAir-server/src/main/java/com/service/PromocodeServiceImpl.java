package com.service;

import com.model.entity.Flight;
import com.model.entity.Promocode;
import com.repository.FlightRepository;
import com.repository.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PromocodeServiceImpl implements PromocodeService{
    @Autowired
    private PromocodeRepository promocodeRepository;

    @Autowired
    private FlightRepository flightRepository;

    public List<Promocode> getPromocodes(){
        return promocodeRepository.findAll();
    }
    public boolean isExistPromocodeByValue(String value){
        List<Promocode> result = promocodeRepository.findByPromocodeValue(value);
        return !result.isEmpty();
    }
    public boolean isPromocodeSuitableByValue(String value, Integer flightID){
        Flight flight = flightRepository.findById(flightID)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not exist with id :" + flightID));
        List<Promocode> promocodes = promocodeRepository.findByPromocodeValue(value);
        if (promocodes.isEmpty()){
            throw new ResourceNotFoundException("Promocode not exist with value :" + value);
        }
        Promocode promocode = promocodes.get(0);
        if ((promocode.getTrack() == flight.getTrack()) && promocode.getUseCount() > 0){
            return true;
        }
        return false;
    }
    @Override
    public Promocode getPromocodeByValue(String value){
        return promocodeRepository.findByPromocodeValue(value).get(0);
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
