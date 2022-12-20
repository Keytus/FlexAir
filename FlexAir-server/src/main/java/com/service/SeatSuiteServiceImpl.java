package com.service;

import com.model.entity.SeatSuite;
import com.repository.SeatSuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatSuiteServiceImpl implements SeatSuiteService{

    @Autowired
    private SeatSuiteRepository seatSuiteRepository;
    @Override
    public List<SeatSuite> getSeatSuites(){
        return seatSuiteRepository.findAll();
    }
    @Override
    public SeatSuite getSeatSuiteByID(Integer id){
        return seatSuiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatSuite not exist with id :" + id));
    }
    @Override
    public void deleteSeatSuiteByID(Integer id){
        SeatSuite seatSuite = seatSuiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatSuite not exist with id :" + id));
        seatSuiteRepository.delete(seatSuite);
    }
    @Override
    public SeatSuite createSeatSuite(SeatSuite seatSuiteData){
        return seatSuiteRepository.save(seatSuiteData);
    }
    @Override
    public SeatSuite updateSeatSuiteByID(Integer id, SeatSuite seatSuiteData){
        SeatSuite seatSuite = seatSuiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SeatSuite not exist with id :" + id));
        seatSuite.setLuxTotal(seatSuiteData.getLuxTotal());
        seatSuite.setFirstClassTotal(seatSuiteData.getFirstClassTotal());
        seatSuite.setEconomyTotal(seatSuiteData.getEconomyTotal());
        seatSuite.setLuxReserved(seatSuiteData.getLuxReserved());
        seatSuite.setFirstClassReserved(seatSuiteData.getFirstClassReserved());
        seatSuite.setEconomyReserved(seatSuiteData.getEconomyReserved());

        return seatSuiteRepository.save(seatSuite);
    }
}
