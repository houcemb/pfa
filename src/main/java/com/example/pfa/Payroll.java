package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Payroll {
    @Id
    private Integer payrollId;
    @ManyToOne
    private Employee employee;
    private  Date date;
    private double amount;
    private double taxes;
    @ManyToOne
    Admin admin;
    @OneToMany (mappedBy = "payroll")
    private List<Attendance> attendanceList;


}
