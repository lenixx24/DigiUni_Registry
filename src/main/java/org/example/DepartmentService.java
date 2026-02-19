package org.example;

import java.util.Scanner;

public class DepartmentService implements ConsoleService{
 private Scanner scanner;
    @Override
    public void createMenu() {
        if(Repository.getFaculties().isEmpty()) {
            System.out.println("You can't create Department without faculties");
            return;
        }
        Department newDepartment = new Department(
                Validator.getCorrectDepartmentID("ID"),
                Validator.getCorrectString("name"),
                Validator.getCorrectFaculty("faculty ID"),
            //    Validator.getCorrectTeacher(" head's ID"),
                Validator.getCorrectString("office"));
        Repository.addDepartment(newDepartment);
        System.out.println(newDepartment.getName()+" created successfully");
    }
    @Override
    public void updateMenu() {
        scanner = new Scanner(System.in);
        System.out.print("Enter department ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Repository.findDepartmentById(id).ifPresentOrElse(
                department -> {
                    System.out.println("\nDepartment found:");
                    System.out.println(department);
                    System.out.println("\nUpdate department:");
                    System.out.println("1. Faculty");
                    System.out.println("2. Head");
                    System.out.println("3. Office");
                    System.out.println("4. Back");

                    int choice = Validator.checkedUserChoice(1, 4);
                    switch (choice) {
                        case 1: department.changeFaculty(Validator.getCorrectFaculty("faculty ID"));break;
                        case 2 : department.changeHead(Validator.getCorrectTeacher("teacher ID")); break;
                        case 3 : department.changeOffice(Validator.getCorrectString("office")); break;
                        case 4 : default: return;
                    }
                    System.out.println("Department updated successfully");
                    System.out.println(department);
                },
                () -> System.out.println("Department not found")
        );
    }
    @Override
    public void removeMenu() {
        scanner = new Scanner(System.in);
        System.out.println("Enter department id: ");
        Department departmentForRemove=null;
        try{
            departmentForRemove=Repository.findDepartmentById(scanner.nextInt()).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find department")
            );
        }
        catch (IllegalArgumentException e){
            System.out.println("Can not find department");
            removeMenu();
            scanner.close();
        }
        if (departmentForRemove != null) {
            Repository.removeDepartment(departmentForRemove);
            System.out.println("Department removed successfully");
        }
    }
    @Override
    public void searchMenu() {
        System.out.print("Enter department ID: ");
        Repository.findDepartmentById(scanner.nextInt())
                .ifPresentOrElse(System.out::println, () -> System.out.println("Department not found"));
    }

    @Override
    public void reportMenu() {
        System.out.println("\nDepartments:\n");
        boolean found = false;
        for (Department d: Repository.getDepartments()) {
            if (d!= null) {
                System.out.println( d);
                found = true;
            }
        }
        if (!found) System.out.println("No departments found");
    }
}
