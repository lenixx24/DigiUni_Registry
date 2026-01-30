package org.example;

public class Department {
    private final int id;
    private final String name;
    private Faculty faculty;
    private Teacher head;
    private int office;
    private Teacher[] teachers =new Teacher[10];
    private int numberOfTeachers;
    private Student[] students =new Student[10];
    private int numberOfStudents;
    public Department(int id, String name, Faculty faculty, Teacher head, int office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.office = office;
    }
    public void addTeacher(Teacher teacher){
        if(numberOfTeachers>=teachers.length){
            throw new ArrayIndexOutOfBoundsException("Too many teachers");
        }
        this.teachers[numberOfTeachers]=teacher;
        numberOfTeachers++;
    }
    public void addStudent(Student student){
        if(numberOfStudents>=students.length){
            throw new ArrayIndexOutOfBoundsException("Too many students");
        }
        this.students[numberOfStudents]=student;
        numberOfStudents++;
    }
}
