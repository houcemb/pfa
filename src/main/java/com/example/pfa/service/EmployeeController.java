package com.example.pfa.service;

import com.example.pfa.Employee;
import com.example.pfa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}

