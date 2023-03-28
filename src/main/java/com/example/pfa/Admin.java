package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin {
    @Id
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private String adminMailAddress;
    @OneToMany(mappedBy = "admin")


    private List<TaxFormGeneration> taxFormGenera;
    @OneToMany(mappedBy = "admin")
    private List<Payroll> payrollList;
    @OneToMany(mappedBy = "admin")
    private List<PerformanceReview> performance;
    @OneToMany(mappedBy = "admin")
    private List<Pto> ptoList;


}
