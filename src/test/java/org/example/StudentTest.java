package org.example;

import exceptions.ValidationException;
import org.example.Entities.*;
import org.junit.jupiter.api.*;
import static org.example.Main.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
        // Tests student changing course logic
    void changeCourse(){
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
        student.changeCourse(6);
        assertEquals(6, student.getCourse());
        assertThrows(ValidationException.class, () -> student.changeCourse(7));

    }

    @Test
        // Tests student age calculation
    void getAge(){
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", LocalDate("30.05.2019"), 30);


        Faculty fi = new Faculty(Repository.Naukma, 1, "Faculty of Informatics",
                "FI", t1, "0444256064", "fin@ukma.edu.ua");

        Department d1 = new Department(1, "Department of Mathematics", fi, t1, "10-7");

        Group SC5 = new Group("SC-5", d1);
        Repository.addGroup(SC5);
        Person person = new Student(5, "Jones", "David", "Allen",
                LocalDate("30.01.2002"), "d.jones@ukma.edu.ua", "0935556677", "KM100555",
                5, SC5, 2021, "budget", "studying"
        );
        assertEquals(24, person.getAge());
        assertFalse(person.getAge() == 25);
    }
}
