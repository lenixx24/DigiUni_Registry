package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class StudentService implements ConsoleService{
    private Scanner scanner;
    private static final Logger log = LogManager.getLogger(StudentService.class);
    @Override
    public void createMenu() {
        Student newStudent = new Student(
                Validator.getCorrectStudentID("ID"),
                Validator.getCorrectString("last name"),
                Validator.getCorrectString("first name"),
                Validator.getCorrectString("middle name"),
                Validator.getCorrectDate("birth date"),
                Validator.getCorrectEmail("email address"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectString("student ID"),
                Validator.getCorrectInt("course"),
                Validator.getCorrectString("group"),
                Validator.getCorrectInt("entry year"),
                Validator.getCorrectString("study form"),
                Validator.getCorrectString("status")
        );
        Repository.addStudent(newStudent);
        newStudent.getAge();
        log.info("Student {} created, Age: {} years",
                newStudent.getFullName(), newStudent.getAge());
    }

    @Override
    public void updateMenu() {
        if(Repository.getStudents().isEmpty()) {
            System.out.println("You have no students for update");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("student ID");

        Repository.findStudentById(id).ifPresentOrElse(
                student -> {
                    System.out.println("\nStudent found:");
                    System.out.println(student);
                    System.out.println("\nUpdate student:");
                    System.out.println("1. Student ID");
                    System.out.println("2. Course");
                    System.out.println("3. Group");
                    System.out.println("4. Study form");
                    System.out.println("5. Status");
                    System.out.println("6. Back");

                    int choice = Validator.checkedUserChoice(1, 6);
                    switch (choice) {
                        case 1: student.changeStudentId(Validator.getCorrectString("student id")); break;
                        case 2 : student.changeCourse(Validator.getCorrectInt("course")); break;
                        case 3 : student.changeGroup(Validator.getCorrectString("group")); break;
                        case 4 : student.changeStudyForm(Validator.getCorrectString("study form")); break;
                        case 5 : student.changeStatus(Validator.getCorrectString("status")); break;
                        case 6 : default: return;
                    }
                    log.info("Student with ID {} updated successfully", id);
                    System.out.println(student);
                },
                () -> log.warn("No student with ID {}", id)
        );

    }

    @Override
    public void removeMenu() {
        if(Repository.getStudents().isEmpty()) {
            System.out.println("You have no students for remove");
            return;
        }
        reportAll();
        scanner = new Scanner(System.in);
        Student studentForRemove=null;
        int id=0;
        try{
            id=Validator.getCorrectInt("student id");
            studentForRemove=Repository.findStudentById(id).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find student")
            );
        }
        catch (IllegalArgumentException e){
            log.warn("No student with ID {}", id);
            removeMenu();
        }
        if (studentForRemove != null) {
            Repository.removeStudent(studentForRemove);
            log.info("Student with ID {} removed successfully",  id);
        }

    }

    @Override
    public void searchMenu() {
        System.out.println("\u001B[34mSearch student by:\u001B[0m");
        scanner=new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch(choice) {
            case 1:
                int id = Validator.getCorrectInt("student ID");
                Repository.findStudentById(id)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No student with ID {}", id));
                break;
            case 2:
                String name = Validator.getCorrectString("full name");
                Repository.findStudentByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No student with name {}", name));
                break;
            case 3: default: break;
        }

    }

    @Override
    public void reportMenu() {
        System.out.println("\nStudents:\n");
        int index = 1;
        boolean found = false;
        for (Student s: Repository.getStudents()) {
            if (s!= null) {
                System.out.println(index++ + ". " + s);
                found = true;
            }
        }
        if (!found) log.info("No students found");

    }
    private void reportAll() {
        System.out.println("\nStudents:\n");
        int index = 1;
        for (Student s : Repository.getStudents()) {
            if (s != null)  System.out.println(index++ + ". " + s);

        }
    }
}
