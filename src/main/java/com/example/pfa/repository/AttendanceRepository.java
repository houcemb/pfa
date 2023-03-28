package com.example.pfa.repository;

import com.example.pfa.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    }


