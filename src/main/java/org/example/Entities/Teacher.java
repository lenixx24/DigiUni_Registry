package org.example.Entities;
import exceptions.DenialException;
import exceptions.ValidationException;

import java.time.LocalDate;
import java.util.*;

public class Teacher extends Person{
    private String job;
    private String degree;
    private String academicStatus;
    private final LocalDate hireDate;
    private double workload;
    private Set<Department> departments = new HashSet<>();

    public void addDepartment(Department department) {
        if (department != null && !this.departments.contains(department)) {
            this.departments.add(department);
            department.addTeacher(this);
        }
    }
    public void removeDepartment(Department department) {

        if (this.departments.size() <= 1) {
            throw new DenialException("Teacher must have at least one department!");
        }
        if (this.departments.remove(department)) {
            department.removeTeacher(this);
        }
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Teacher(int id, String lastName, String firstName, String middleName, LocalDate birthDate, String email, String phone, String job, String degree, String academicStatus, LocalDate hireDate, double workload) {
        super(id, lastName, firstName, middleName, birthDate, email, phone);
        this.job = job;
        this.degree = degree;
        this.academicStatus = academicStatus;
        this.hireDate = hireDate;
        this.workload = workload;
    }



    public void changeJob(String newJob){
        if(newJob==null||newJob.isEmpty()){
            throw new IllegalArgumentException("Job must not be empty");
        }
        this.job=newJob;
    }
    public String getJob() {
        return job;
    }
    public void changeDegree(String newDegree){
        if(newDegree==null||newDegree.isEmpty()){
            throw new IllegalArgumentException("Degree must not be empty");
        }
        this.degree=newDegree;
    }
    public void changeAcademicStatus(String newAcademicStatus){
        if(newAcademicStatus==null||newAcademicStatus.isEmpty()){
            throw new IllegalArgumentException("Academic status must not be empty");
        }
        this.academicStatus=newAcademicStatus;
    }
    public void changeWorkload(double newWorkload){
        if(newWorkload<=0||Double.isNaN(newWorkload)){
            throw new ValidationException("Workload must be positive");
        }
        this.workload=newWorkload;
    }

    @Override
    public String toString() {
        return super.toString()+", Job: "+job+", Degree: "+degree+", Academic status: "+academicStatus+", Department(s): "+dep()+", Hired on: "+hireDate+", Workload: "+workload;
    }

    private String dep() {
        return departments.stream()
                .map(Department::dep)
                .reduce((a, b) -> a + ", " + b).orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
