package com.example.demo.Repository;

import com.example.demo.Entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findByUserIdAndProjectId(Long userId, Long projectId);
    List<Rate> findByProjectId(Long projectId);
    Long countByProjectId(Long projectId);
}
