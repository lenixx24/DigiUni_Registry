package org.example;

public class Faculty {
  //public final University university;
    private final int id;
    private final String fullName;
    private final String shortName;
    private Teacher dean;
    private String phoneNumber;
    private String emailAddress;
    private Department[] departments=new Department[10];
    private int numberOfDepartments;

    public Faculty(int id, String fullName, String shortName, Teacher dean, String phoneNumber, String emailAddress) {
       //this.university=university;
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.dean = dean;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        //university.addFaculty(this);
    }
    public void addDepartment(Department department){
        if(numberOfDepartments>=departments.length){
            throw new ArrayIndexOutOfBoundsException("Too many departments");
        }
        departments[numberOfDepartments]=department;
        numberOfDepartments++;
    }

    public int getNumberOfDepartments() {
        return numberOfDepartments;
    }
public String getShortName(){
    return shortName;
}
    @Override
    public String toString() {
        return fullName+" ("+shortName+"), Dean: "+dean+", Contacts: "+phoneNumber+", "
                +emailAddress;
    }
}
