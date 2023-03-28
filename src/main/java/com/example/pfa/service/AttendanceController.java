package com.example.pfa.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.pfa.Employee;
import com.example.pfa.repository.AttendanceRepository;
import com.example.pfa.Attendance;
import com.example.pfa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;
 @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/logEnter")
    public void logEnter(@RequestParam("employeeId") int employeeId) {
        Attendance attendance = new Attendance();

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new RuntimeException("Employee not found"));
        attendance.setEmployee(employee);
        attendance.setInTime(new Timestamp(System.currentTimeMillis()));
        attendanceRepository.save(attendance);
    }

    @PostMapping("/logExit")
    public void logExit(int employeeId) {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Find the attendance record for the current date
        Optional<Attendance> optionalAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now());

        if (optionalAttendance.isPresent()) {
            // Update the exit time for the existing attendance record
            Attendance attendance = optionalAttendance.get();
            attendance.setOutTime(Timestamp.valueOf(now));
            attendanceRepository.save(attendance);
        } else {
            // Attendance record not found for the current date
            throw new RuntimeException("Attendance record not found for employee ID: " + employeeId);
        }
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }
}
I







@GetMapping("/list")
    public List<Attendance> list() {
        return attendanceRepository.findAll();
    }
}
