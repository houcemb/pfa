package com.example.pfa.service;

import com.example.pfa.Employee;
import com.example.pfa.SuperVisor;
import com.example.pfa.repository.EmployeeRepository;
import com.example.pfa.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
    @Autowired
    private SupervisorRepository supervisorRepository;
    @PostMapping("/register")
    public ResponseEntity<?> registerSupervisor( @RequestBody SuperVisor supervisor) {
        // save the supervisor object to the database
        supervisorRepository.save(supervisor);
        return new ResponseEntity<>("Supervisor registered successfully", HttpStatus.CREATED);
    }
    @GetMapping("{supervisorId}/employees")
    public ResponseEntity<List<Employee>> getSupervisedEmployees(@PathVariable Integer supervisorId) {
        Optional<SuperVisor> supervisorOptional = supervisorRepository.findById(supervisorId);
        if (supervisorOptional.isPresent()) {
            SuperVisor supervisor = supervisorOptional.get();
            List<Employee> employees = supervisor.getEmployees();
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
