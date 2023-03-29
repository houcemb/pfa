package com.example.pfa.service;

import com.example.pfa.Attendance;
import com.example.pfa.Employee;
import com.example.pfa.repository.AttendanceRepository;
import com.example.pfa.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AttendanceControllerTest {
    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AttendanceController attendanceController;

    @Test
    void logEnter() {


        // Set up mock data
        int employeeId = 1;
        Employee employee = new Employee();
        employee.setId(employeeId);
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);

        attendance.setInTime(new Timestamp(System.currentTimeMillis()));

        // Set up mock behavior
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

        // Call the method
        attendanceController.logEnter(employeeId);

        // Verify the results
        verify(employeeRepository).findById(employeeId);
        verify(attendanceRepository).save(attendance);
    }


    @Test
    void logExit() {
    }
}