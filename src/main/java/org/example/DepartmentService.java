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
        newDepartment.getFaculty().addDepartment(newDepartment);
        System.out.println(newDepartment.getName()+" created successfully");
    }
    @Override
    public void updateMenu() {
        if(Repository.getDepartments().isEmpty()) {
            System.out.println("No departments found");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("department ID");
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
                        case 2 : if(Repository.getTeachers().isEmpty()) {
                            System.out.println("No teachers found");
                            return;
                        }
                            department.changeHead(Validator.getCorrectTeacher("teacher ID")); break;
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
        if(Repository.getDepartments().isEmpty()){
            System.out.println("No departments found");
            return;
        }
        reportAll();
        Department departmentForRemove=null;
        try{
            departmentForRemove=Repository.findDepartmentById(Validator.getCorrectInt("department ID")).orElseThrow(
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
        if(Repository.getDepartments().isEmpty()){
            System.out.println("You have no departments");
            return;
        }
        System.out.println("\u001B[34mSearch department by:\u001B[0m");
        scanner = new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch (choice) {
            case 1:
                Repository.findDepartmentById(Validator.getCorrectInt("department ID")).ifPresentOrElse(
                        System.out::println, () -> System.out.println("Department not found"));
                break;
            case 2:
                Repository.findDeparmentByName(Validator.getCorrectString("department name")).ifPresentOrElse(
                        System.out::println, () -> System.out.println("Department not found"));
                break;
            case 3:
            default:
                break;
        }
    }

    @Override
    public void reportMenu() {
        if(Repository.getDepartments().isEmpty()) {
            System.out.println("No departments found");
            return;
        }
        reportAll();
    }
    private void reportAll(){
        System.out.println("\nDepartments:\n");
        int index=1;
        for (Department d: Repository.getDepartments()) {
            if (d!= null) System.out.println( (index++)+". "+d);
        }
    }
}
