package com.service;

import com.model.entity.Promocode;
import com.repository.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocodeServiceImpl implements PromocodeService{
    @Autowired
    private PromocodeRepository promocodeRepository;

    public List<Promocode> getPromocodes(){
        return promocodeRepository.findAll();
    }
    public boolean isExistPromocodeByValue(String value){
        List<Promocode> result = promocodeRepository.findByPromocodeValue(value);
        return !result.isEmpty();
    }
}
