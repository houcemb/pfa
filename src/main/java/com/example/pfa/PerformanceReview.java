package com.example.pfa;

import jakarta.persistence.Entity;
import jakarta.persistence.*;



@Entity
public class PerformanceReview {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    private Employee employee;

    private String employeeName;


    private String department;


    private String region;


    private String education;


    private String gender;


    private String recruitmentChannel;

    public PerformanceReview(Long id, Employee employee, String employeeName, String department, String region, String education, String gender, String recruitmentChannel, Integer noOfTrainings, Integer age, Integer previousYearRating, Integer lengthOfService, Admin admin, Boolean awardsWon, Integer avgTrainingScore, SuperVisor supervisor) {
        this.id = id;
        this.employee = employee;
        this.employeeName = employeeName;
        this.department = department;
        this.region = region;
        this.education = education;
        this.gender = gender;
        this.recruitmentChannel = recruitmentChannel;
        this.noOfTrainings = noOfTrainings;
        this.age = age;
        this.previousYearRating = previousYearRating;
        this.lengthOfService = lengthOfService;
        this.admin = admin;
        this.awardsWon = awardsWon;
        this.avgTrainingScore = avgTrainingScore;
        this.supervisor = supervisor;
    }

    private Integer noOfTrainings;

    private Integer age;

    private Integer previousYearRating;


    private Integer lengthOfService;
    @ManyToOne
    private Admin admin;


    private Boolean awardsWon;


    private Integer avgTrainingScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRecruitmentChannel() {
        return recruitmentChannel;
    }

    public void setRecruitmentChannel(String recruitmentChannel) {
        this.recruitmentChannel = recruitmentChannel;
    }

    public Integer getNoOfTrainings() {
        return noOfTrainings;
    }

    public void setNoOfTrainings(Integer noOfTrainings) {
        this.noOfTrainings = noOfTrainings;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPreviousYearRating() {
        return previousYearRating;
    }

    public void setPreviousYearRating(Integer previousYearRating) {
        this.previousYearRating = previousYearRating;
    }

    public Integer getLengthOfService() {
        return lengthOfService;
    }

    public void setLengthOfService(Integer lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public Boolean getAwardsWon() {
        return awardsWon;
    }

    public void setAwardsWon(Boolean awardsWon) {
        this.awardsWon = awardsWon;
    }

    public Integer getAvgTrainingScore() {
        return avgTrainingScore;
    }

    public void setAvgTrainingScore(Integer avgTrainingScore) {
        this.avgTrainingScore = avgTrainingScore;
    }

    public SuperVisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SuperVisor supervisor) {
        this.supervisor = supervisor;
    }

    @ManyToOne()

    private SuperVisor supervisor;

    public PerformanceReview() {
    }
}



