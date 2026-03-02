package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Department {
    private final int id;
    private final String name;
    private Faculty faculty;
    private Teacher head;
    private String office;
    private Set<Teacher> teachers =new HashSet<>();
    private int numberOfTeachers;
    private Set<Student> students = new HashSet<>();
    private int numberOfStudents;
    public Department(int id, String name, Faculty faculty, Teacher head, String office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.office = office;
    }
    public Department(int id, String name, Faculty faculty, String office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.office = office;
        faculty.addDepartment(this);
    }
    public void addTeacher(Teacher teacher){
        if (teachers.add(teacher)) {
            numberOfTeachers++;
        }
    }
    public void addStudent(Student student){
        if (students.add(student)) {
            numberOfStudents++;
        }
    }
    public void removeStudent(Student student){
        if (students.remove(student)) {
            Repository.removeStudent(student);
            numberOfStudents--;
        }
    }
    public void removeTeacher(Teacher teacher){
        if (teachers.remove(teacher)) {
            Repository.removeTeacher(teacher);
            numberOfTeachers--;
        }
    }
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void changeFaculty(Faculty newFaculty){
        if(newFaculty==null){
            throw new IllegalArgumentException("Faculty must not be null");
        }
        faculty.removeDepartment(this);
        this.faculty=newFaculty;
        faculty.addDepartment(this);
    }
    public void changeHead(Teacher newHead){
        if(newHead==null){
            throw new IllegalArgumentException("Head must not be null");
        }
        this.head=newHead;
    }
    public void changeOffice(String newOffice){
        if(newOffice==null||newOffice.isEmpty()){
            throw new IllegalArgumentException("Office must not be empty");
        }
        this.office=newOffice;
    }

    @Override
    public String toString() {
        if(head==null) return "ID: "+id+", "+name+" ("+ faculty.getShortName()+"),\n Head: -, Office: "+office;
        return "ID: "+id+", "+name+" ("+ faculty.getShortName()+"),\n Head: "+head.getFullName()+
                ", Office: "+office;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }
}
