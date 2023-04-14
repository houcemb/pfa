package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.DayOfWeek;
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

    public double calculateGrossSalary(LocalDate startDate, LocalDate endDate) {

        double baseSalary = employee.getBaseSalary();
        double totalHoursWorked = 0.0;
        double totalExtraHours = 0.0;
        double totalMissingHours = 0.0;
        //calculate total working days for the month

        int workingDays = 0;

        int daysInMonth = startDate.lengthOfMonth();

        for (int i = 1; i <= daysInMonth; i++) {
            DayOfWeek dayOfWeek = startDate.withDayOfMonth(i).getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                workingDays++;
            }
        }
        int attendanceSize = attendanceList.size();

        for (Attendance attendance : attendanceList) {
            LocalDate attendanceDate = attendance.getAttendanceDate();
            if (attendanceDate.isAfter(startDate.minusDays(1)) && attendanceDate.isBefore(endDate.plusDays(1))) {
                double hoursWorked = attendance.getHoursWorked();
                double extraHours = attendance.getOvertimeHours();
                double missingHours = 0;
                if (hoursWorked < 8) {
                    missingHours = 8 - hoursWorked;
                }

                totalHoursWorked += hoursWorked;
                totalExtraHours += extraHours;
                totalMissingHours += missingHours;
            }
        }

        double hourRate = baseSalary / 170;
        double salary = employee.getBaseSalary();
        salary -= (workingDays - attendanceSize) * 8 * hourRate;
        salary += (hourRate * 1.5 * totalExtraHours);
        salary -= hourRate * totalMissingHours;

        return salary;
    }

    public static double calculateNetSalary(double grossSalary) {
        // Calculate social security contributions
        double socialSecurity = grossSalary * 0.09;

        // Calculate health insurance
        double healthInsurance = grossSalary * 0.01;

        // Calculate income tax
        double taxableIncome = grossSalary - socialSecurity - healthInsurance;
        double incomeTax;
        if (taxableIncome <= 5000) {
            incomeTax = 0;
        } else if (taxableIncome <= 25000) {
            incomeTax = (taxableIncome - 5000) * 0.15;
        } else if (taxableIncome <= 50000) {
            incomeTax = (taxableIncome - 25000) * 0.25 + 3000;
        } else {
            incomeTax = (taxableIncome - 50000) * 0.35 + 10500;
        }

        // Calculate net salary

        return grossSalary - socialSecurity - healthInsurance - incomeTax;
    }
}



