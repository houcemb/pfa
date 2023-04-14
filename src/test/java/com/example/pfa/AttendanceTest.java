package com.example.pfa;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttendanceTest {
    private Attendance attendance;

    @BeforeEach
    void setUp() throws Exception {
        attendance = new Attendance();
    }

    @Test
    void testGetHoursWorkedWithValidData() {
        Timestamp inTime = Timestamp.valueOf("2023-03-30 09:00:00");
        Timestamp outTime = Timestamp.valueOf("2023-03-30 18:00:00");
        attendance.setInTime(inTime);
        attendance.setOutTime(outTime);
        double expected = 9.0;
        double actual = attendance.getHoursWorked();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void testGetHoursWorkedWithMissingData() {
        attendance.setInTime(null);
        attendance.setOutTime(null);
        double expected = 0.0;
        double actual = attendance.getHoursWorked();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void testGetOvertimeHoursWithValidData() {
        Timestamp inTime = Timestamp.valueOf("2023-03-30 09:00:00");
        Timestamp outTime = Timestamp.valueOf("2023-03-30 19:00:00");
        attendance.setInTime(inTime);
        attendance.setOutTime(outTime);
        double expected = 2.0;
        double actual = attendance.getOvertimeHours();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    void testGetOvertimeHoursWithMissingData() {
        attendance.setInTime(null);
        attendance.setOutTime(null);
        double expected = 0.0;
        double actual = attendance.getOvertimeHours();
        assertEquals(expected, actual, 0.001);
    }
    @Test
    void testGetOvertimeHoursWithNoOvertime() {
        Timestamp inTime = Timestamp.valueOf("2023-03-30 09:00:00");
        Timestamp outTime = Timestamp.valueOf("2023-03-30 18:00:00");
        attendance.setInTime(inTime);
        attendance.setOutTime(outTime);
        double expected = 0.0;
        double actual = attendance.getOvertimeHours();
        assertEquals(expected, actual, 0.001);
    }
}
