package com.example.pfa.service;

import com.example.pfa.Employee;
import com.example.pfa.Pto;

import com.example.pfa.repository.EmployeeRepository;
import com.example.pfa.repository.PtoReopsitory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Pto")
public class PtoController {
    @Autowired
    PtoReopsitory ptoRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    private Integer employeeID;

    @PostMapping("/request")
    public Pto ptoRequest(Integer employeeID , @RequestBody Pto pto) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
        Employee employee = optionalEmployee.orElseThrow(() -> new RuntimeException("Employee not found"));
        pto.setEmployee(employee);
        pto.setStatus("pending");
        return ptoRepository.save(pto);
    }
    @PostMapping ("/approve")
    public Pto ptoApprove( Integer ptoID){
        Pto pto = ptoRepository.findById(ptoID)
                .orElseThrow(() -> new IllegalArgumentException("PTO with id " + ptoID + " not found"));
        pto.setStatus("approved");
        return ptoRepository.save(pto);

    }
    @PostMapping ("/deny")
    public Pto ptoDeny( Integer ptoID){
        Pto pto = ptoRepository.findById(ptoID)
                .orElseThrow(() -> new IllegalArgumentException("PTO with id " + ptoID + " not found"));
        pto.setStatus("denied");
        return ptoRepository.save(pto);

    }


}
