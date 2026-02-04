package org.example;

public class Faculty {
    public final University university;
    private final int id;
    private final String fullName;
    private final String shortName;
    private Teacher dean;
    private String phoneNumber;
    private String emailAddress;
    private Department[] departments=new Department[10];
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
    public void addDepartment(Department department){
        if(numberOfDepartments>=departments.length){
            throw new ArrayIndexOutOfBoundsException("Too many departments");
        }
        departments[numberOfDepartments]=department;
        numberOfDepartments++;
    }
    public void removeDepartment(Department department){
        for(int i=0; i<numberOfDepartments; i++){
            if (departments[i].equals(department)) {
                for(int j=i; j<numberOfDepartments-1; j++)
                    departments[j]=departments[j+1];
                departments[numberOfDepartments-1]=null;
                numberOfDepartments--;
                return;
            }
        }
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
    @Override
    public String toString() {
        return fullName+" ("+shortName+"), Dean: "+dean+", Contacts: "+phoneNumber+", "
                +emailAddress;
    }
}
