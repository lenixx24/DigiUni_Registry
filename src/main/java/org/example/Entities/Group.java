package org.example.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return name.equals(group.name) && department.equals(group.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department);
    }
}
