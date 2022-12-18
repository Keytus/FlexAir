package com.repository;

import com.model.entity.InfoBlock;
import com.model.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findByIsInWheel(Boolean inWheel);
}
