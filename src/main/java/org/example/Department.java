package org.example;

public class Department {
    private final int id;
    private final String name;
    private Faculty faculty;
    private Teacher head;
    private int office;

    public Department(int id, String name, Faculty faculty, Teacher head, int office) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.office = office;
    }
}
