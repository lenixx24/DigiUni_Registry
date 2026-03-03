package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Main.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DepartmentTest {

    @Test
        // Tests student adding to department
    void addStudentToDepartment(){
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", LocalDate("30.05.2019"), 30);


        Faculty fi = new Faculty(Repository.Naukma, 1, "Faculty of Informatics",
                "FI", t1, "0444256064", "fin@ukma.edu.ua");


        Department d1 = new Department(1, "Department of Mathematics", fi, t1, "10-7");

        Group SC5 = new Group("SC-5", d1);
        Repository.addGroup(SC5);
        Student student = new Student(5, "Jones", "David", "Allen",
                LocalDate("30.01.2002"), "d.jones@ukma.edu.ua", "0935556677", "KM100555",
                5, SC5, 2021, "budget", "studying"
        );

        d1.addStudent(student);
        d1.addStudent(student);

        d1.removeStudent(student);
        assertEquals(0,d1.getNumberOfStudents());
        assertFalse(d1.getNumberOfStudents()==1);

    }
}
