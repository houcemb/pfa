package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Payroll {
    @Id
    private Integer payrollId;

    public Payroll() {

    }

    public Integer getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Integer payrollId) {
        this.payrollId = payrollId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDateOfPayroll() {
        return dateOfPayroll;
    }

    public void setDateOfPayroll(Date dateOfPayroll) {
        this.dateOfPayroll = dateOfPayroll;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public Payroll(Integer payrollId, Employee employee, Date dateOfPayroll, double amount, double taxes, Admin admin, List<Attendance> attendanceList) {
        this.payrollId = payrollId;
        this.employee = employee;
        this.dateOfPayroll = dateOfPayroll;
        this.amount = amount;
        this.taxes = taxes;
        this.admin = admin;
        this.attendanceList = attendanceList;
    }

    @ManyToOne
    private Employee employee;
    private Date dateOfPayroll;
    private double amount;
    private double taxes;
    @ManyToOne
    private Admin admin;
    @OneToMany(mappedBy = "payroll")
    private List<Attendance> attendanceList;

    // Other properties and methods omitted for brevity

    public double calculateSalary(LocalDate startDate, LocalDate endDate) {

        double baseSalary = employee.getBaseSalary();
        double totalHoursWorked = 0.0;
        double totalExtraHours = 0.0;
        double totalMissingHours = 0.0;

        for (Attendance attendance : employee.getAttendances()) {
            LocalDate attendanceDate = attendance.getAttendanceDate();
            if (attendanceDate.isAfter(startDate.minusDays(1)) && attendanceDate.isBefore(endDate.plusDays(1))) {
                double hoursWorked = attendance.getHoursWorked();
                double extraHours = attendance.getOvertimeHours();
                double missingHours =0;
                if(hoursWorked<8){
                 missingHours = 8 - hoursWorked;}

                totalHoursWorked += hoursWorked;
                totalExtraHours += extraHours;
                totalMissingHours += missingHours;
            }
        }
        double hourRate = baseSalary / 170;
        double salary = employee.getBaseSalary();
        salary += (hourRate*1.5 * totalExtraHours);
        salary -=  hourRate* totalMissingHours;

        return salary;
    }

}



