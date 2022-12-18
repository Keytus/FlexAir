package com.repository;

import com.model.entity.InfoBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoBlockRepository extends JpaRepository<InfoBlock, Integer> {
    List<InfoBlock> findByHeader(String header);
}
