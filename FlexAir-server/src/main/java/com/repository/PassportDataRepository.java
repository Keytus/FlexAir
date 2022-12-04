package com.repository;

import com.model.entity.PassportData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDataRepository extends JpaRepository<PassportData, Integer> {
}
