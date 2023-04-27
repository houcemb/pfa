package com.example.pfa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
public class SuperVisor extends Employee implements UserDetails {


    public SuperVisor(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR")); // Supervisor role in addition to the ROLE_EMPLOYEE
        return authorities;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    @JsonManagedReference

    @OneToMany(mappedBy = "supervisor",fetch = FetchType.EAGER)
    private List<Employee> employees;

    public SuperVisor(Integer id, String name, String email, String password, Date hireDate, String department, String postion, List<Payroll> payrollList) {
        super(id, name, email, password, hireDate, department, postion, payrollList);
    }

    public SuperVisor() {
    }
}
