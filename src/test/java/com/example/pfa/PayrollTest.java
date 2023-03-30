package com.example.pfa;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayrollTest {

    @BeforeEach
    void setUp() throws Exception {
        Payroll payroll = new Payroll();
    }

    @Test
    void testCalculateSalaryWithValidData() {
        Payroll payroll = new Payroll();
        Employee employee = new Employee();
        employee.setBaseSalary(2000);
        List<Attendance> attendances = new ArrayList<>();
        Attendance attendance1 = new Attendance();
        attendance1.setAttendanceDate(LocalDate.of(2023, 3, 29));
        attendance1.setInTime(Timestamp.valueOf("2023-03-29 09:00:00"));
        attendance1.setOutTime(Timestamp.valueOf("2023-03-29 18:00:00"));
        attendances.add(attendance1);
        Attendance attendance2 = new Attendance();
        attendance2.setAttendanceDate(LocalDate.of(2023, 3, 30));
        attendance2.setInTime(Timestamp.valueOf("2023-03-30 09:00:00"));
        attendance2.setOutTime(Timestamp.valueOf("2023-03-30 19:00:00"));
        attendances.add(attendance2);
        employee.setAttendances(attendances);
        payroll.setEmployee(employee);

        double expected = 2017.47;
        double actual = payroll.calculateSalary(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31));
        assertEquals(expected, actual, 0.5);
    }

    @Test
    void testCalculateSalaryWithMissingData() {
        Payroll payroll = new Payroll();
        Employee employee = new Employee();
        employee.setBaseSalary(2000);
        List<Attendance> attendances = new ArrayList<>();
        employee.setAttendances(attendances);
        payroll.setEmployee(employee);

        double expected = 2000;
        double actual = payroll.calculateSalary(LocalDate.of(2023, 3, 1), LocalDate.of(2023, 3, 31));
        assertEquals(expected, actual, 0.01);
    }
}