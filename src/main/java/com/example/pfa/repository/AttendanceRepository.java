package com.example.pfa.repository;

import com.example.pfa.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
        Optional<Attendance> findByEmployeeIdAndAttendanceDate(Integer employeeId, LocalDate date);

    List<Attendance> findByAttendanceDate(LocalDate date);
}


