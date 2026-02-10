package org.example;

public class Department {
    private final int id;
    private final String name;
    private Faculty faculty;
    private Teacher head;
    private String office;
    private Teacher[] teachers =new Teacher[10];
    private int numberOfTeachers;
    private Student[] students =new Student[10];
    private int numberOfStudents;
    public Department(int id, String name, Faculty faculty, Teacher head, String office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.office = office;
        faculty.addDepartment(this);
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
    public void removeStudent(Student student){
        for(int i=0; i<numberOfStudents; i++){
            if (students[i].equals(student)) {
                for(int j=i; j<numberOfStudents-1; j++)
                    students[j]=students[j+1];
                students[numberOfStudents-1]=null;
                numberOfStudents--;
                return;
            }
        }
    }
    public void removeTeacher(Teacher teacher){
        for(int i=0; i<numberOfTeachers; i++){
            if (teachers[i].equals(teacher)) {
                for(int j=i; j<numberOfTeachers-1; j++)
                    teachers[j]=teachers[j+1];
                teachers[numberOfTeachers-1]=null;
                numberOfTeachers--;
                return;
            }
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
        return id+": "+name+" ("+ faculty.getShortName()+"), Head: "+head+
                ", Office: "+office;
    }
}
