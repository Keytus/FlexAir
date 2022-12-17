package com.service;

import com.model.entity.Promocode;

import java.util.List;

public interface PromocodeService {

    public List<Promocode> getPromocodes();
    public boolean isExistPromocodeByValue(String value);
}
