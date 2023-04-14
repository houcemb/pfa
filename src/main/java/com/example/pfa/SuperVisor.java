package com.example.pfa;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Entity
public class SuperVisor extends Employee implements UserDetails {


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
    }


    @OneToMany(mappedBy = "supervisor")
    private List<Employee> employees;

    public SuperVisor(Integer id, String name, String email, String password, Date hireDate, String department, String postion, List<Payroll> payrollList) {
        super(id, name, email, password, hireDate, department, postion, payrollList);
    }

    public SuperVisor() {
    }
}
