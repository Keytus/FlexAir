package com.repository;

import com.model.entity.Offer;
import com.model.entity.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromocodeRepository extends JpaRepository<Promocode, Integer> {
    List<Promocode> findByPromocodeValue(String promocodeValue);
}
