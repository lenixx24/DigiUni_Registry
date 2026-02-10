package org.example;

public class Teacher extends Person{
    private String job;
    private String degree;
    private String academicStatus;
    private final String hireDate;
    private double workload;

    public Teacher(int id, String lastName, String firstName, String middleName, String birthDate, String email, String phone, String job, String degree, String academicStatus, String hireDate, double workload) {
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
            throw new IllegalArgumentException("Workload must be positive");
        }
        this.workload=newWorkload;
    }

    @Override
    public String toString() {
        return super.toString()+", Job: "+job+", Degree: "+degree+", Academic status: "+academicStatus+", Hired on: "+hireDate+", Workload: "+workload;
    }


}
