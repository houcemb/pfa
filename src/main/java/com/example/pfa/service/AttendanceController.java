package com.example.pfa.service;

import java.sql.Timestamp;
import java.time.LocalDate;
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

    @PostMapping("/logEnter/{employeeId}")
    public void logEnter(@PathVariable Integer employeeId) {
        Attendance attendance = new Attendance();

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.orElseThrow(() -> new RuntimeException("Employee not found"));
        attendance.setEmployee(employee);
        Date date = new Date();
        LocalDate date1 =  LocalDate.now();
        attendance.setInTime(new Timestamp(date.getTime()));
        attendance.setAttendanceDate(date1);

        attendanceRepository.save(attendance);
    }

    @PostMapping("/logExit/{employeeId}")
    public void logExit(@PathVariable Integer employeeId) {
        // Get the current date and time
        LocalDate now = LocalDate.now();

        // Find the attendance record for the current date
        Optional<Attendance> optionalAttendance = attendanceRepository.findByEmployeeIdAndAttendanceDate(employeeId, now);

        if (optionalAttendance.isPresent()) {
            // Update the exit time for the existing attendance record
            Date date = new Date();
       //     LocalDate date1 =  LocalDate.now();

            Attendance attendance = optionalAttendance.get();
            attendance.setOutTime(new Timestamp(date.getTime()));
            attendanceRepository.save(attendance);
        } else {
            // Attendance record not found for the current date
            throw new RuntimeException("Attendance record not found for employee ID: " + employeeId);
        }
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }

    @GetMapping("/list")
    public List<Attendance> list() {
        return attendanceRepository.findAll();
    }
    @GetMapping("/employee/{employeeID}")
        public List<Attendance> getEmployeeAttendance(@PathVariable Integer employeeID) {
        Employee employee =employeeRepository.findById(employeeID).orElseThrow(()-> new RuntimeException("Employee not found"));
        return employee.getAttendances();
    }
}





