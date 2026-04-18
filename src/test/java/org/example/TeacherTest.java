package org.example;

import org.example.Entities.Teacher;
import org.junit.jupiter.api.*;
import static org.example.Main.Main.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {

    @Test
    // Tests teacher changing job
    void changeJob() {
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", LocalDate("30.05.2019"), 30);
        t1.changeJob("newJob");
        assertEquals("newJob",t1.getJob() );
        assertThrows(IllegalArgumentException.class, () -> t1.changeJob(""));

    }
}
