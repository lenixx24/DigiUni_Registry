package org.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String name;
    private final Department department;
    private List<Student> students = new ArrayList<>();

    public Group(String name, Department department) {
        this.name = name;
        this.department = department;
       }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public String getName() { return name; }
    public Department getDepartment() { return department; }
    public List<Student> getStudents() { return students; }

    @Override
    public String toString() {
        return name + " (Department: " + department.getName() + ")";
    }
}
