package org.example;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final int id;
    private final String name;
    private Faculty faculty;
    private Teacher head;
    private String office;
    private List<Teacher> teachers =new ArrayList<>();
    private int numberOfTeachers;
    private List<Student> students =new ArrayList<>();
    private int numberOfStudents;
    public Department(int id, String name, Faculty faculty, Teacher head, String office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.office = office;
        faculty.addDepartment(this);
    }
    public Department(int id, String name, Faculty faculty, String office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.office = office;
        faculty.addDepartment(this);
    }
    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
        numberOfTeachers++;
    }
    public void addStudent(Student student){
        this.students.add(student);
        numberOfStudents++;
    }
    public void removeStudent(Student student){
        Repository.removeStudent(student);
        students.remove(student);
        numberOfStudents--;
    }
    public void removeTeacher(Teacher teacher){
       Repository.removeTeacher(teacher);
       teachers.remove(teacher);
       numberOfTeachers--;
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
}
