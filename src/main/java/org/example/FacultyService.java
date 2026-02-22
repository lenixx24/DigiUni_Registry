package org.example;

import java.util.Scanner;

public class FacultyService implements ConsoleService {
    private Scanner scanner;

    @Override
    public void createMenu() {
        Faculty newFaculty = new Faculty(Repository.Naukma,
                Validator.getCorrectFacultyID("ID"),
                Validator.getCorrectString("full name"),
                Validator.getCorrectString("short name"),
                //  Validator.getCorrectTeacher(" dean's ID"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectEmail("email address"));
        Repository.addFaculty(newFaculty);
        Repository.Naukma.addFaculty(newFaculty);
        System.out.println(newFaculty.getShortName() + " created successfully");
    }

    @Override
    public void updateMenu() {
        if(Repository.getFaculties().isEmpty()) {
            System.out.println("You have no faculties for update");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("faculty ID");
        Repository.findFacultyById(id).ifPresentOrElse(
                faculty -> {
                    System.out.println("\nFaculty found:");
                    System.out.println(faculty);
                    System.out.println("\nUpdate faculty:");
                    System.out.println("1. Dean");
                    System.out.println("2. Phone number");
                    System.out.println("3. Email address");
                    System.out.println("4. Back");

                    int choice = Validator.checkedUserChoice(1, 4);
                    switch (choice) {
                        case 1:
                            if(Repository.getTeachers().isEmpty()) {
                                System.out.println("No teachers found");
                                break;
                            }
                            faculty.changeDean(Validator.getCorrectTeacher("teacher ID"));
                            break;
                        case 2:
                            faculty.changePhoneNumber(Validator.getCorrectPhoneNumber("phone number"));
                            break;
                        case 3:
                            faculty.changeEmailAddress(Validator.getCorrectEmail("email address"));
                            break;
                        case 4:
                        default:
                            return;
                    }
                    System.out.println("Faculty updated successfully");
                    System.out.println(faculty);
                },
                () -> System.out.println("Faculty not found")
        );
    }

    @Override
    public void removeMenu() {
        if(Repository.getFaculties().isEmpty()) {
            System.out.println("You have no faculties for remove");
            return;
        }
        reportAll();
        scanner = new Scanner(System.in);
        System.out.println("Enter faculty id: ");
        Faculty facultyForRemove = null;
        try {
            facultyForRemove = Repository.findFacultyById(scanner.nextInt()).orElseThrow(
                    () -> new IllegalArgumentException("Can not find faculty")
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Can not find faculty");
            removeMenu();
            scanner.close();
        }
        if (facultyForRemove != null) {
            Repository.removeFaculty(facultyForRemove);
            System.out.println("Faculty removed successfully");
        }
    }

    @Override
    public void searchMenu() {
        if(Repository.getFaculties().isEmpty()) {
            System.out.println("You have no faculties");
            return;
        }
        System.out.println("\u001B[34mSearch faculty by:\u001B[0m");
        scanner = new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Short name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch (choice) {
            case 1:
                Repository.findFacultyById(Validator.getCorrectInt("faculty ID")).ifPresentOrElse(
                        System.out::println, () -> System.out.println("Faculty not found"));
                break;
            case 2:
                Repository.findFacultyByShortName(Validator.getCorrectString("faculty short name")).ifPresentOrElse(
                        System.out::println, () -> System.out.println("Faculty not found"));
                break;
            case 3:
            default:
                break;
        }

    }

    @Override
    public void reportMenu() {
        if(Repository.getFaculties().isEmpty()){
            System.out.println("No faculties found");
            return;
        }
        reportAll();
    }

    private void reportAll() {
        System.out.println("\nFaculties:\n");
        int index = 1;
        for (Faculty f : Repository.getFaculties()) {
            if (f != null)  System.out.println(index++ + ". " + f);

        }
    }
}