package com.example.pfa;

import com.example.pfa.Admin;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Year;

@Entity
public class TaxFormGeneration {
    @Id
    private Integer formId;
    private Year taxYear;
    private String formType;
    @ManyToOne
    Admin admin;/*
    public void generateIR1Form(Employee employee, int year) {
        // Get the required data for the IR1 form from the Employee object
        String name = employee.getName();


        double annualSalary = employee.getBaseSalary()*12;

        // Generate the IR1 form using a template and populate it with the required data
        Document ir1Form = new Document();
        ir1Form.add("Name", name);
        ir1Form.add("Address", address);
        ir1Form.add("Social Security Number", socialSecurityNumber);
        ir1Form.add("Annual Salary", annualSalary);
        // Save the generated IR1 form as a PDF or Word document
        ir1Form.save("IR1 Form " + socialSecurityNumber + " " + year + ".pdf");
    }

    // Generate the Social Security and National Health Insurance Declaration (CNSS) for the specified employee and year
    public void generateCNSSForm(Employee employee, int year) {
        // Get the required data for the CNSS form from the Employee object
        String name = employee.getName();

        double annualSalary = employee.getBaseSalary()*12;


        // Generate the CNSS form using a template and populate it with the required data
        Document cnssForm = new Document();
        cnssForm.add("Name", name);
        cnssForm.add("Address", address);
        cnssForm.add("Social Security Number", socialSecurityNumber);
        cnssForm.add("Annual Salary", annualSalary);
        cnssForm.add("Social Security Contribution", socialSecurityContribution);
        cnssForm.add("National Health Insurance Contribution", nationalHealthInsuranceContribution);
        // Save the generated CNSS form as a PDF or Word document
        cnssForm.save("CNSS Form " + socialSecurityNumber + " " + year + ".pdf");
    }*/
}
