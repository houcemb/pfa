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


}
