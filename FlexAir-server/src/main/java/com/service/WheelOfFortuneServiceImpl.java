package com.service;

import com.model.entity.Track;
import com.repository.PromocodeRepository;
import com.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WheelOfFortuneServiceImpl implements WheelOfFortuneService{
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private PromocodeRepository promocodeRepository;

    @Override
    public List<Track> getFortuneTracks(){
        return trackRepository.findByIsInWheel(true);
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
