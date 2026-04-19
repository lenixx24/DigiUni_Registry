package org.example.Entities;

import exceptions.ValidationException;
import org.example.Main.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Faculty {
    private final University university;
    private final int id;
    private String fullName;
    private String shortName;
    private Teacher dean;
    private String phoneNumber;
    private String emailAddress;
    private List<Department> departments=new ArrayList<>();
    private List<Student> students=new ArrayList<>();
    private List<Teacher> teachers=new ArrayList<>();
    private int numberOfDepartments;

    public Faculty(University university, int id, String fullName, Teacher dean, String phoneNumber, String emailAddress) {
        this.university=university;
        this.id = id;
        this.fullName = fullName;
        setShortName();
        this.dean = dean;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public Faculty(University university, int id, String fullName, String phoneNumber, String emailAddress) {
        this.university=university;
        this.id = id;
        this.fullName = fullName;
        setShortName();
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public void addDepartment(Department department){
        departments.add(department);
        numberOfDepartments++;
    }
    public void removeDepartment(Department department){
     if(department==null) return;
       if(departments.remove(department))
            numberOfDepartments--;
    }
    public void addTeacher(Teacher teacher) {
        if (teacher != null && !teachers.contains(teacher)) {
            teachers.add(teacher);
        }
    }
    public void addStudent(Student student) {
        if (student != null && !students.contains(student)) {
            students.add(student);
        }
    }
    public int getNumberOfDepartments() {
        return numberOfDepartments;
    }
    public Teacher getDean() {
        return dean;
    }
    public List<Department> getDepartments() {
        return departments;
    }
    public List<Student> getStudents() {
        return students;
    }
    public List<Teacher> getTeachers() {
        return teachers;
    }
    public int getID() {
        return id;
    }
    public String getShortName(){
        return shortName;
    }
    public String getFullName(){
        return fullName;
    }

    public void changeDean(Teacher newDean){
        if(newDean==null){
            throw new ValidationException("Dean must not be null");
        }
        this.dean=newDean;
    }
    public void changePhoneNumber(String newPhoneNumber){
        if(newPhoneNumber==null||newPhoneNumber.isEmpty()){
            throw new ValidationException("Phone number must not be empty");
        }
        this.phoneNumber=newPhoneNumber;
    }
    public void changeEmailAddress(String newEmailAddress){
        if(newEmailAddress==null||newEmailAddress.isEmpty()){
            throw new ValidationException("Email address must not be empty");
        }
        this.emailAddress=newEmailAddress;
    }
    @Override
    public String toString() {
        String toStart;
        if(dean==null) {
            toStart ="ID: "+id+", "+fullName+" ("+shortName+"), \nDean: -, Contacts: "+phoneNumber+", "
                    +emailAddress;
        }else{
            toStart ="ID: "+id+", "+fullName+" ("+shortName+"), \nDean: "+dean.getFullName()+", Contacts: "+phoneNumber+", "
                    +emailAddress;
        }

        String dep;
        if (departments.isEmpty()) {
            dep = "none";
        } else {
            dep = departments.toString();
        }
        return toStart + ", \n\u001B[1mDepartments:\u001B[0m\n" + dep;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && Objects.equals(shortName, faculty.shortName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, shortName);
    }

    public void setShortName() {
        String[] words = fullName.split(" ");
        this.shortName="";
        for(String w: words){
            if(w.equalsIgnoreCase("of")) continue;
            this.shortName +=w.charAt(0);
        }
        this.shortName=this.shortName.toUpperCase();
    }

    public void changeFullName(String fullName) {
        if(fullName==null||fullName.isEmpty()){
            throw new ValidationException("Name must not be empty");
        }
        this.fullName=fullName;
        setShortName();
    }
}
