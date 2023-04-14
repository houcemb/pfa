package com.example.pfa;

import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PayrollTest {

    private Payroll payroll;
    private Employee employee;
    private List<Attendance> attendance;

    @BeforeEach
    public void setUp() {
        payroll = new Payroll();
        employee = mock(Employee.class);
        payroll.setEmployee(employee);
        Admin admin = mock(Admin.class);

    }

    @Test
    public void testCalculateSalary() {
        LocalDate startDate = LocalDate.of(2022, 3, 1);
        LocalDate endDate = LocalDate.of(2022, 3, 31);
        attendance =new ArrayList<>();

        // Set up the employee's base salary and hours worked in March
        when(employee.getBaseSalary()).thenReturn(5000.0);
        for (int i = 1; i <= 23; i++) {
            attendance.add(new Attendance(LocalDate.of(2022, 3, i), Timestamp.valueOf("2023-03-29 09:00:00"), Timestamp.valueOf("2023-03-29 18:00:00")));
        }



        payroll.setAttendanceList(attendance);


        when(employee.getBaseSalary()).thenReturn(5000.0);

        // Calculate the expected salary
        double hourRate = 5000.0 / 170;
        double expectedSalary = 5000.0 - ((31-8-2)*8*hourRate) + (hourRate * 1.5 * 3.5) - (hourRate * 5);

        // Call the method being tested
        double actualSalary = payroll.calculateSalary(startDate, endDate);

        // Assert that the expected salary matches the actual salary
        assertEquals(5000, actualSalary, 0.001);
    }
}
