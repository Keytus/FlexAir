package com.service;

import com.model.entity.Track;

import java.util.List;

public interface WheelOfFortuneService {
    public List<Track> getFortuneTracks();
    public String generatePromocodeValue();
}
