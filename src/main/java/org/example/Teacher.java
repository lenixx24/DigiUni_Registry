package org.example;

class Teacher extends Person{
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


}
