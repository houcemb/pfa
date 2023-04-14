package com.example.pfa.repository;

import com.example.pfa.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<PerformanceReview, Integer> {
}
