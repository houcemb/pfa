package com.example.pfa.repository;

import com.example.pfa.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
     Employee findByEmail(String userEmail) ;

    Employee getEmployeeById(Integer employeeId);
}
