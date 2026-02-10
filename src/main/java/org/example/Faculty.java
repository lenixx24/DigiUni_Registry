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
        return fullName+" ("+shortName+"), Dean: "+dean+", Contacts: "+phoneNumber+", "
                +emailAddress;
    }
}
