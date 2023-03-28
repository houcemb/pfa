package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity

public class Pto {
    @Id
    private Integer ptoId;
    @ManyToOne
    private Employee employee;
    private String description;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    Admin admin;

    public Pto() {
    }

    public Pto(Integer ptoId, Employee employee, String description, Date startDate, Date endDate, Admin admin) {
        this.ptoId = ptoId;
        this.employee = employee;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.admin = admin;
    }

    public Integer getPtoId() {
        return ptoId;
    }

    public void setPtoId(Integer ptoId) {
        this.ptoId = ptoId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
