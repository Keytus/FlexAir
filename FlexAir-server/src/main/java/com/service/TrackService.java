package com.service;

import com.model.dto.TrackDTO;
import com.model.entity.Track;

import java.util.List;

public interface TrackService {
    public List<Track> getTracks();
    public Track getTrackByID(Integer id);
    public void deleteTrackByID(Integer id);
    public Track createTrack(TrackDTO trackData);
    public Track updateTrackByID(Integer id, TrackDTO trackData);
}
