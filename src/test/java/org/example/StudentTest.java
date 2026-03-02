package org.example;

import org.junit.jupiter.api.*;
import static org.example.Main.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
        // Tests student changing course logic
    void changeCourse(){
        Student student = new Student(5, "Jones", "David", "Allen",
                LocalDate("30.01.2002"), "d.jones@ukma.edu.ua", "0935556677", "KM100555",
                5, "SC-5", 2021, "budget", "studying"
        );
        student.changeCourse(6);
        assertEquals(6, student.getCourse());
        assertThrows(IllegalArgumentException.class, () -> student.changeCourse(7));

    }

    @Test
        // Tests student age calculation
    void getAge(){
        Person person = new Student(5, "Jones", "David", "Allen",
                LocalDate("30.01.2002"), "d.jones@ukma.edu.ua", "0935556677", "KM100555",
                5, "SC-5", 2021, "budget", "studying"
        );
        assertEquals(24, person.getAge());
        assertFalse(person.getAge() == 25);
    }
}
