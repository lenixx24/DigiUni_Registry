package org.example;

public class Faculty {
    private final int id;
    private final String fullName;
    private final String shortName;
    private Teacher dean;
    private String phoneNumber;
    private String emailAddress;
    private Department[] departments=new Department[10];
    private int numberOfDepartments;

    public Faculty(int id, String fullName, String shortName, Teacher dean, String phoneNumber, String emailAddress) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.dean = dean;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public void addDepartment(Department department){
        if(numberOfDepartments>=departments.length){
            throw new ArrayIndexOutOfBoundsException("Too many departments");
        }
        departments[numberOfDepartments]=department;
        numberOfDepartments++;
    }
}
