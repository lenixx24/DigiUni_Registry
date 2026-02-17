package org.example;

import java.util.Scanner;

public class FacultyService implements ConsoleService{
    private Scanner scanner;
    @Override
    public void createMenu() {
        Faculty newFaculty = new Faculty(Repository.Naukma,
                Validator.getCorrectFacultyID("ID"),
                Validator.getCorrectString("full name"),
                Validator.getCorrectString("short name"),
                Validator.getCorrectTeacher(" dean's ID"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectEmail("email address"));
        Repository.addFaculty(newFaculty);
        System.out.println(newFaculty.getShortName()+" created successfully");
    }
    @Override
    public void updateMenu() {
        scanner = new Scanner(System.in);
        System.out.print("Enter faculty ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
                        case 1: faculty.changeDean(Validator.getCorrectTeacher("teacher ID"));break;
                        case 2 : faculty.changePhoneNumber(Validator.getCorrectPhoneNumber("phone number"));break;
                        case 3 : faculty.changeEmailAddress(Validator.getCorrectEmail("email address"));break;
                        case 4 : default: //updateMenu();
                    }
                    System.out.println("Faculty updated successfully");
                    System.out.println(faculty);
                },
                () -> System.out.println("Faculty not found")
        );
    }
    @Override
    public void removeMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter faculty id: ");
        Faculty facultyForRemove=null;
        try{
            facultyForRemove=Repository.findFacultyById(scanner.nextInt()).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find faculty")
            );
        }
        catch (IllegalArgumentException e){
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
        scanner = new Scanner(System.in);
        System.out.print("Enter faculty ID: ");
        Repository.findFacultyById(scanner.nextInt())
                .ifPresentOrElse(System.out::println, () -> System.out.println("Faculty not found"));
    }
    @Override
    public void reportMenu() {
        System.out.println("\nFaculties:\n");
        int index = 1;
        boolean found = false;
        for (Faculty f: Repository.getFaculties()) {
            if (f!= null) {
                System.out.println(index++ + ". " + f);
                found = true;
            }
        }
        if (!found) System.out.println("No faculties found");
    }
}
