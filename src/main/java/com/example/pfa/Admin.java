package com.example.pfa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Admin implements UserDetails {
    @Id
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private String adminMailAddress;
    //@JsonManagedReference
    @OneToMany(mappedBy = "admin")


    private List<TaxFormGeneration> taxFormGenera;
    @JsonManagedReference(value = "admin-payroll")
    @OneToMany(mappedBy = "admin")
    private List<Payroll> payrollList;
    @JsonManagedReference(value = "performance-admin")
    @OneToMany(mappedBy = "admin")
    private List<PerformanceReview> performance;

    public Admin() {
    }

    public Admin(Integer adminId, String adminName, String adminPassword, String adminMailAddress) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminMailAddress = adminMailAddress;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminMailAddress() {
        return adminMailAddress;
    }

    public void setAdminMailAddress(String adminMailAddress) {
        this.adminMailAddress = adminMailAddress;
    }

    public List<TaxFormGeneration> getTaxFormGenera() {
        return taxFormGenera;
    }

    public void setTaxFormGenera(List<TaxFormGeneration> taxFormGenera) {
        this.taxFormGenera = taxFormGenera;
    }

    public List<Payroll> getPayrollList() {
        return payrollList;
    }

    public void setPayrollList(List<Payroll> payrollList) {
        this.payrollList = payrollList;
    }

    public List<PerformanceReview> getPerformance() {
        return performance;
    }

    public void setPerformance(List<PerformanceReview> performance) {
        this.performance = performance;
    }

    public List<Pto> getPtoList() {
        return ptoList;
    }

    public void setPtoList(List<Pto> ptoList) {
        this.ptoList = ptoList;
    }

    @OneToMany(mappedBy = "admin")
    private List<Pto> ptoList;


    @Override
    public String getPassword() {
        return adminPassword;
    }

    @Override
    public String getUsername() {
        return adminMailAddress;
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

    public Admin(String adminName, String adminPassword, String adminMailAddress) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.adminMailAddress = adminMailAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // Supervisor role in addition to the ROLE_EMPLOYEE
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
