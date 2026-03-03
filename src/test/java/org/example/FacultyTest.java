package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Main.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class FacultyTest {

    @Test
        // Tests faculty changing dean
    void changeDean() {
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", LocalDate("30.05.2019"), 30);

        Teacher t2 = new Teacher(2, "Mazin", "Dmitro", "Mykhaylovich",
                LocalDate("01.01.1980"), "mazindm@ukma.edu.ua", "0444251420",
                "Dean", "Candidate of Philological Sciences", "Docent", LocalDate("01.01.2009"), 30);

        Faculty fi = new Faculty(Repository.Naukma, 1, "Faculty of Informatics",
                "FI", t1, "0444256064", "fin@ukma.edu.ua");

        assertEquals(t1, fi.getDean());

        fi.changeDean(t2);
        assertEquals(2, fi.getDean().getId());
        assertFalse(t1.equals(fi.getDean()));

        assertThrows(IllegalArgumentException.class, () -> fi.changeDean(null));
    }

    @Test
        // Tests Faculty and Department connection
    void DepartmentAndFacultyTest() {
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", LocalDate("30.05.2019"), 30);
        Teacher t2 = new Teacher(2, "Mazin", "Dmitro", "Mykhaylovich",
                LocalDate("01.01.1980"), "mazindm@ukma.edu.ua", "0444251420",
                "Dean", "Candidate of Philological Sciences", "Docent", LocalDate("01.01.2009"), 30);
        Teacher t4 = new Teacher(4, "Chornei", "Ruslan", "Konstyantynovych",
                LocalDate("01.01.1980"), "r.chornei@ukma.edu.ua", "0681982970",
                "Head", "Candidate of Physical and Mathematical Sciences", "Docent", LocalDate("01.01.2009"), 30);

        Faculty fi = new Faculty(Repository.Naukma, 1, "Faculty of Informatics",
                "FI", t1, "0444256064", "fin@ukma.edu.ua");
        Faculty fhn = new Faculty(Repository.Naukma, 2, "Faculty of Humanities",
                "FHN", t2, "0444251420", "mazindm@ukma.edu.ua");


        // When the department is created, it should be automatically added to the faculty list
        int initialCount = fi.getNumberOfDepartments();
        Department d1 = new Department(1, "Department of Mathematics", fi, t4, "10-7");
        fi.addDepartment(d1);

        assertEquals(initialCount + 1, fi.getNumberOfDepartments(),"Test1");
        assertTrue(fi.toString().contains("Department of Mathematics"), "Test2");


        // Department is aware of faculty, and vice versa
        assertEquals(fi, d1.getFaculty(), "Test3");

        // Change department placement from fi to fhn
        d1.changeFaculty(fhn);

        // Check that the department is no longer in fi
        assertFalse(fi.toString().contains("Department of Mathematics"), "Test4");
        assertEquals(0, fi.getNumberOfDepartments(), "Test5");

        // Check that the department is now in fhn
        assertTrue(fhn.toString().contains("Department of Mathematics"), "Test6");
        assertEquals(1, fhn.getNumberOfDepartments(), "Test7");
        assertEquals(fhn, d1.getFaculty(), "Test8");

        // Check that the department is removed from fhn
        fhn.removeDepartment(d1);
        assertEquals(0, fhn.getNumberOfDepartments(), "Test9");
        assertFalse(fhn.toString().contains("Department of Mathematics"), "Test10");
        assertThrows(IllegalArgumentException.class, () -> d1.changeFaculty(null), "Test11");

    }

}
