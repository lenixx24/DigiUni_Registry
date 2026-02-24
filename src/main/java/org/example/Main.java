package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static LocalDate LocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
    public static void main(String[] args) {
        Teacher t1 = new Teacher(1, "Glybovets", "Andriy", "Mykolayovich",
                LocalDate("25.10.1980"), "a.glybovets@ukma.edu.ua", "0444636985",
                "Dean", "Doctor of Technical Sciences", "Docent", "30.05.2019", 30);
        Repository.addTeacher(t1);
        Faculty fi = new Faculty(Repository.Naukma, 1, "Faculty of Informatics",
                "FI", t1, "0444256064", "fin@ukma.edu.ua");
        Repository.addFaculty(fi);
        Teacher t2 = new Teacher(2, "Mazin", "Dmitro", "Mykhaylovich",
                LocalDate("01.01.1980"), "mazindm@ukma.edu.ua", "0444251420",
                "Dean", "Candidate of Philological Sciences", "Docent", "01.01.2009", 30);
        Repository.addTeacher(t2);
        Faculty fhn = new Faculty(Repository.Naukma, 2, "Faculty of Humanities",
                "FHN", t2, "0444251420", "mazindm@ukma.edu.ua");
        Repository.addFaculty(fhn);
        Teacher t3 = new Teacher(3, "Glushcenko", "Svitlana", "Vasylivna",
                LocalDate("01.01.1980"), "gluschenkosv@ukma.edu.ua", "0444256042",
                "Dean", "Candidate of Economic Sciences", "Docent", "01.01.2009", 30);
        Repository.addTeacher(t3);
        Faculty fen = new Faculty(Repository.Naukma, 3, "Faculty of Economic Sciences",
                "FEN", t3, "0444251420", "gluschenkosv@ukma.edu.ua");
        Repository.addFaculty(fen);
        Teacher t4 = new Teacher(4, "Chornei", "Ruslan", "Konstyantynovych",
                LocalDate("01.01.1980"), "r.chornei@ukma.edu.ua", "0681982970",
                "Head", "Candidate of Physical and Mathematical Sciences", "Docent", "01.01.2009", 30);
        Repository.addTeacher(t4);
        Department d1 = new Department(1, "Department of Mathematics", fi, t4, "10-7");
        Repository.addDepartment(d1);
        Teacher t5 = new Teacher(5, "Herchanivska", "Polina", "Evaldivna",
                LocalDate("01.01.1980"), "p.herchanivska@ukma.edu.ua", "0444256098",
                "Head", "Doctor of Cultural Studies", "Professor","01.01.2009", 30);
        Repository.addTeacher(t5);
        Department d2 = new Department(2,  "Department of cultural studies", fhn, t5, "1-316");
        Repository.addDepartment(d2);
        Teacher t6 = new Teacher(6, "Lukyanenko", "Iryna", "Gryhorivna",
                LocalDate("01.01.1980"), "iryna.lukianenko@ukma.edu.ua", "0444256042",
                "Head", "Doctor of of Economic Sciences", "Professor", "01.01.2009", 30);
        Repository.addTeacher(t6);
        Department d3 = new Department(3,  "Department of Finance", fen, t6, "6-410");
        Repository.addDepartment(d3);
        Student s1 = new Student(1, "Smith", "John", "Edward",
                LocalDate("15.05.2006"), "john.smith@ukma.edu.ua", "0501112233", "KM100101",
                1, "CS-1", 2025, "budget", "studying");
    Repository.addStudent(s1);
    d1.addStudent(s1);
    Student s2 = new Student(2, "Johnson", "Emily", "Rose",
            LocalDate("20.11.2004"), "e.johnson@ukma.edu.ua", "0672223344", "KM100305",
                3, "SE-3", 2023, "contract", "studying");
        Repository.addStudent(s2);
        d1.addStudent(s2);
        Student s3 = new Student(3, "Williams", "Michael", "James",
                LocalDate("10.03.2005"), "m.williams@ukma.edu.ua", "0633334455", "KM100222",
                2, "LB-2", 2024, "budget", "academic leave");
        Repository.addStudent(s3);
        d2.addStudent(s3);
        Student s4 = new Student(4, "Brown", "Sarah", "Elizabeth",
                LocalDate("25.07.2003"), "sarah.brown@ukma.edu.ua", "0984445566", "KM100410",
                4, "EC-4", 2022, "contract", "expelled"
        );
        Repository.addStudent(s4);
        d3.addStudent(s4);
        Student s5 = new Student(5, "Jones", "David", "Allen",
                LocalDate("30.01.2002"), "d.jones@ukma.edu.ua", "0935556677", "KM100555",
                5, "SC-5", 2021, "budget", "studying"
        );
        Repository.addStudent(s5);
        d2.addStudent(s5);
        Student s6 = new Student(6, "Garcia", "Maria", "Ann",
                LocalDate("12.09.2001"), "m.garcia@ukma.edu.ua", "0506667788", "KM100601",
                6, "BW-6", 2020, "contract", "studying");
        Repository.addStudent(s6);
        d3.addStudent(s6);
        Menu.startMenu();
    }
}
