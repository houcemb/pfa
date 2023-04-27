package com.example.pfa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer attendanceId;
    private LocalDate attendanceDate;

    private Timestamp inTime;
    private Timestamp outTime;
    @JsonBackReference

    @ManyToOne
    private Employee employee;

    public Attendance() {
    }

    public Attendance(LocalDate attendanceDate, Timestamp inTime, Timestamp outTime) {
        this.attendanceDate = attendanceDate;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }



    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getOutTime() {
        return outTime;
    }

    public void setOutTime(Timestamp outTime) {
        this.outTime = outTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Payroll getPayroll() {
        return payroll;
    }

    public void setPayroll(Payroll payroll) {
        this.payroll = payroll;
    }

    @ManyToOne
    private Admin admin;
    @ManyToOne
    private Payroll payroll;
    public double getHoursWorked() {
        if (inTime == null || outTime == null) {
            return 0.0;
        }

        long diff = outTime.getTime() - inTime.getTime();
        return (double) diff / (1000 * 60 * 60)-1;
    }

    public double getOvertimeHours() {
        if (inTime == null || outTime == null) {
            return 0.0;
        }

        double hours = getHoursWorked();
        if (hours <= 8.0) {
            return 0.0;
        }

        return hours - 8.0;
    }
}
