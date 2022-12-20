package com.service;

import com.model.entity.SeatSuite;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface SeatSuiteService {
    public List<SeatSuite> getSeatSuites();
    public void deleteSeatSuiteByID(Integer id);
    public SeatSuite createSeatSuite(SeatSuite seatSuiteData);
    public SeatSuite updateSeatSuiteByID(Integer id, SeatSuite seatSuiteData);
    public SeatSuite getSeatSuiteByID(Integer id);
}
