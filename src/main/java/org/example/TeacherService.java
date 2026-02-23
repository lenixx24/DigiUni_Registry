package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TeacherService implements ConsoleService{
    private Scanner scanner;
    private static final Logger log = LogManager.getLogger(TeacherService.class);
    @Override
    public void createMenu() {
        Teacher newTeacher = new Teacher(
                Validator.getCorrectTeacherID("ID"),
                Validator.getCorrectString("last name"),
                Validator.getCorrectString("first name"),
                Validator.getCorrectString("middle name"),
                Validator.getCorrectString("birth date"),
                Validator.getCorrectEmail("email address"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectString("job"),
                Validator.getCorrectString("academic degree"),
                Validator.getCorrectString("academic status"),
                Validator.getCorrectString("hire date"),
                Validator.getCorrectInt("workload")
        );
        Repository.addTeacher(newTeacher);
        log.info("{} created successfully", newTeacher.getFullName());
    }

    @Override
    public void updateMenu() {
        int id = Validator.getCorrectInt("teacher ID");

        Repository.findTeacherById(id).ifPresentOrElse(
                teacher -> {
                    System.out.println("\nTeacher found:");
                    System.out.println(teacher);
                    System.out.println("\nUpdate teacher:");
                    System.out.println("1. Job");
                    System.out.println("2. Degree");
                    System.out.println("3. Academic status");
                    System.out.println("4. Workload");
                    System.out.println("5. Back");

                    int choice = Validator.checkedUserChoice(1, 5);
                    switch (choice) {
                        case 1: teacher.changeJob(Validator.getCorrectString("job")); break;
                        case 2 : teacher.changeDegree(Validator.getCorrectString("degree")); break;
                        case 3 : teacher.changeAcademicStatus(Validator.getCorrectString("academic status")); break;
                        case 4 : teacher.changeWorkload(Validator.getCorrectInt("workload")); break;
                        case 5 : default: updateMenu();
                    }
                    log.info("Teacher with ID {} updated successfully", id);
                    System.out.println(teacher);
                },
                () -> log.warn("No teacher with ID {}", id)
        );

    }

    @Override
    public void removeMenu() {
        Teacher teacherForRemove=null;
        int id=Validator.getCorrectInt("teacher ID");
        try{
            teacherForRemove=Repository.findTeacherById(id).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find teacher")
            );
        }
        catch (IllegalArgumentException e){
            log.warn("No teacher with ID {}", id);
            removeMenu();
        }
        Repository.removeTeacher(teacherForRemove);
        if (teacherForRemove != null) {
            log.info("Teacher with ID {} removed successfully", id);
        }

    }

    @Override
    public void searchMenu() {
        System.out.println("\u001B[34mSearch teacher by:\u001B[0m");
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch(choice) {
            case 1:
                int id = Validator.getCorrectInt("teacher ID");
                Repository.findTeacherById(id)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No teacher with ID {}", id));
                break;
            case 2:
                String name = Validator.getCorrectString("full name");
                Repository.findTeacherByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No teacher with name {}", name));
                break;
            case 3: default: break;
        }

    }

    @Override
    public void reportMenu() {
        System.out.println("\nTeachers:\n");
        int index = 1;
        boolean found = false;
        for (Teacher t: Repository.getTeachers()) {
            if (t!= null) {
                System.out.println(index++ + ". " + t);
                found = true;
            }
        }
        if (!found) log.info("No teachers found");

    }


}
