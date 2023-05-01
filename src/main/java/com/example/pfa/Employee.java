package com.example.pfa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeId;
    private String name;
    private String email;
    @JsonBackReference(value = "test")
    @ManyToOne

    private SuperVisor supervisor;
    private String password;
    private Date hireDate;
    private Date terminationDate;
    private String department;
    private String postion;
    @JsonManagedReference(value = "attendance-employee")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
    @JsonManagedReference(value = "performance-employee")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee")
    private List<PerformanceReview> performanceReviewList;
    @JsonManagedReference(value = "-superpayroll")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Payroll> payrollList;
   // @JsonManagedReference(value = "pto-employee")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee")
    private List<Pto> ptoList;

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    private double baseSalary;

    public Employee(Integer id, String name, String email, String password, Date hireDate, String department, String postion, List<Payroll> payrollList) {
        this.employeeId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.department = department;
        this.postion = postion;
        this.payrollList = payrollList;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<PerformanceReview> getPerformanceReviewList() {
        return performanceReviewList;
    }

    public void setPerformanceReviewList(List<PerformanceReview> performanceReviewList) {
        this.performanceReviewList = performanceReviewList;
    }

    public List<Payroll> getPayrollList() {
        return payrollList;
    }

    public void setPayrollList(List<Payroll> payrollList) {
        this.payrollList = payrollList;
    }

    public List<Pto> getPtoList() {
        return ptoList;
    }

    public void setPtoList(List<Pto> ptoList) {
        this.ptoList = ptoList;
    }

    public Employee(Integer id, String name, String email, String password, Date hireDate, Date terminationDate, String department, String postion) {
        this.employeeId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.department = department;
        this.postion = postion;
    }

    public Employee(Integer id, String name, String email, String password, Date hireDate, String department, String postion) {
        this.employeeId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.department = department;
        this.postion = postion;
    }

    public Employee() {
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE")); // All employees have the ROLE_EMPLOYEE
        return authorities;
    }
    public Integer getId() {
        return employeeId;
    }

    public void setId(Integer id) {
        this.employeeId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public Employee(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(Date terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public void setSupervisor(SuperVisor supervisor) {
        this.supervisor = supervisor;
    }

    public SuperVisor getSupervisor() {
        return supervisor;
    }
}
