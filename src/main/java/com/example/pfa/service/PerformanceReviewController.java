package com.example.pfa.service;
import com.example.pfa.Employee;
import com.example.pfa.SuperVisor;
import com.example.pfa.repository.EmployeeRepository;
import com.example.pfa.repository.PerformanceRepository;
import com.example.pfa.PerformanceReview;
import com.example.pfa.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/performanceReview")

public class PerformanceReviewController {
    @Autowired
    PerformanceRepository performanceReviewRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SupervisorRepository supervisorRepository;
    @GetMapping("/performance-reviews")
    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReviews() {
        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAll();
        return ResponseEntity.ok(performanceReviews);
    }
    @GetMapping("/performance-reviews/{reviewId}")
    public ResponseEntity<PerformanceReview> getPerformanceReviewById(@PathVariable Integer reviewId) {
        Optional<PerformanceReview> reviewOptional = performanceReviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            PerformanceReview review = reviewOptional.get();
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/employees/{employeeId}/performance-reviews")
    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReviewsForEmployee(@PathVariable Integer employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            List<PerformanceReview> performanceReviews = employeeOptional.get().getPerformanceReviewList();
            return ResponseEntity.ok(performanceReviews);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{performanceReviewId}")
    public ResponseEntity<PerformanceReview> updatePerformanceReview(@PathVariable Integer performanceReviewId,
                                                                     @RequestBody PerformanceReview performanceReview) {
        Optional<PerformanceReview> performanceReviewOptional = performanceReviewRepository.findById(performanceReviewId);
        if (performanceReviewOptional.isPresent()) {
            PerformanceReview existingPerformanceReview = performanceReviewOptional.get();
            existingPerformanceReview.setEmployee(performanceReview.getEmployee());
            existingPerformanceReview.setEmployeeName(performanceReview.getEmployeeName());
            existingPerformanceReview.setDepartment(performanceReview.getDepartment());
            existingPerformanceReview.setEducation(performanceReview.getEducation());
            existingPerformanceReview.setGender(performanceReview.getGender());
            existingPerformanceReview.setNoOfTrainings(performanceReview.getNoOfTrainings());
            existingPerformanceReview.setAge(performanceReview.getAge());
            existingPerformanceReview.setPreviousYearRating(performanceReview.getPreviousYearRating());
            existingPerformanceReview.setLengthOfService(performanceReview.getLengthOfService());

            existingPerformanceReview.setAwardsWon(performanceReview.getAwardsWon());
            existingPerformanceReview.setAvgTrainingScore(performanceReview.getAvgTrainingScore());
            existingPerformanceReview.setSupervisor(performanceReview.getSupervisor());

            PerformanceReview updatedPerformanceReview = performanceReviewRepository.save(existingPerformanceReview);
            return ResponseEntity.ok(updatedPerformanceReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/employees/{employeeId}/supervisors/{supervisorId}/performance-reviews")
    public ResponseEntity<?> createPerformanceReview(@PathVariable Integer employeeId, @PathVariable Integer supervisorId, @RequestBody PerformanceReview performanceReview) throws Exception {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new Exception("Employee not found with id " + employeeId));
        SuperVisor supervisor = supervisorRepository.findById(supervisorId).orElseThrow(() -> new Exception("Supervisor not found with id " + supervisorId));
        performanceReview.setEmployee(employee);
        performanceReview.setSupervisor(supervisor);
       performanceReviewRepository.save(performanceReview);
        return new ResponseEntity<>("Supervisor registered successfully", HttpStatus.CREATED);
    }

}
