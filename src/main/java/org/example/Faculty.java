package org.example;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    public final University university;
    private final int id;
    private final String fullName;
    private final String shortName;
    private Teacher dean;
    private String phoneNumber;
    private String emailAddress;
    private List<Department> departments=new ArrayList<>();
    private int numberOfDepartments;

    public Faculty(University university, int id, String fullName, String shortName, Teacher dean, String phoneNumber, String emailAddress) {
        this.university=university;
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.dean = dean;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        university.addFaculty(this);
    }
    public Faculty(University university, int id, String fullName, String shortName, String phoneNumber, String emailAddress) {
        this.university=university;
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        university.addFaculty(this);
    }
    public void addDepartment(Department department){
        departments.add(department);
        numberOfDepartments++;
    }
    public void removeDepartment(Department department){
       departments.remove(department);
       Repository.removeDepartment(department);
       numberOfDepartments--;
    }
    public int getNumberOfDepartments() {
        return numberOfDepartments;
    }
    public int getID() {
        return id;
    }
    public String getShortName(){
        return shortName;
    }

    public void changeDean(Teacher newDean){
        if(newDean==null){
            throw new IllegalArgumentException("Dean must not be null");
        }
        this.dean=newDean;
    }
    public void changePhoneNumber(String newPhoneNumber){
        if(newPhoneNumber==null||newPhoneNumber.isEmpty()){
            throw new IllegalArgumentException("Phone number must not be empty");
        }
        this.phoneNumber=newPhoneNumber;
    }
    public void changeEmailAddress(String newEmailAddress){
        if(newEmailAddress==null||newEmailAddress.isEmpty()){
            throw new IllegalArgumentException("Email address must not be empty");
        }
        this.emailAddress=newEmailAddress;
    }
    @Override
    public String toString() {
        return "ID: "+id+", "+fullName+" ("+shortName+"), \nDean: "+dean.getFullName()+", Contacts: "+phoneNumber+", "
                +emailAddress;
    }
}
