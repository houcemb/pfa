package com.example.pfa.service;

import com.example.pfa.Employee;
import com.example.pfa.SuperVisor;
import com.example.pfa.repository.EmployeeRepository;
import com.example.pfa.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;
    public UserDetails loadUserByUsername(String userEmail) {
        return employeeRepository.findByEmail(userEmail);
    }

    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody Employee employee, @RequestParam(name = "supervisorId", required = false) Integer supervisorId) {
        if (supervisorId != null ) {
            SuperVisor supervisor = (SuperVisor) employeeRepository.findById(supervisorId).orElse(null);
            if (supervisor != null){
            employee.setSupervisor(supervisor);

            supervisor.getEmployees().add(employee);
            supervisorRepository.save(supervisor);}

        }
        if (employee.getAttendances() == null) {
            employee.setAttendances(new ArrayList<>());
            if (employee.getPayrollList() == null) {
                employee.setPayrollList(new ArrayList<>());
            }
            if (employee.getPerformanceReviewList() == null) {
                employee.setPerformanceReviewList(new ArrayList<>());
            }
            if (employee.getAttendances() == null) {
                employee.setAttendances(new ArrayList<>());
            }

        }
        return employeeRepository.save(employee);
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeDetails) throws Exception {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found for this id :: " + employeeId));

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPassword(employeeDetails.getPassword());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setTerminationDate(employeeDetails.getTerminationDate());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setPostion(employeeDetails.getPostion());
        employee.setBaseSalary(employeeDetails.getBaseSalary());

        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Integer employeeId) throws Exception {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found with id " + employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }
}


