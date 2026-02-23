package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DepartmentService implements ConsoleService{

    private static final Logger log = LogManager.getLogger(DepartmentService.class);
    @Override
    public void createMenu() {
        if(Repository.getFaculties().isEmpty()) {
            log.info("You can't create Department without faculties");
            return;
        }
        Department newDepartment = new Department(
                Validator.getCorrectDepartmentID("ID"),
                Validator.getCorrectString("name"),
                Validator.getCorrectFaculty("faculty ID"),
                Validator.getCorrectString("office"));
        Repository.addDepartment(newDepartment);
        newDepartment.getFaculty().addDepartment(newDepartment);
        log.info("{} created successfully", newDepartment.getName());
    }
    @Override
    public void updateMenu() {
        if(Repository.getDepartments().isEmpty()) {
            log.info("No departments found");
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
                            log.warn("No teachers found");
                            return;
                        }
                            department.changeHead(Validator.getCorrectTeacher("teacher ID")); break;
                        case 3 : department.changeOffice(Validator.getCorrectString("office")); break;
                        case 4 : default: return;
                    }
                   log.info("Department with ID {} updated successfully", id);
                    System.out.println(department);
                },
                () -> log.warn("No department with ID {}", id)
        );
    }
    @Override
    public void removeMenu() {
        if(Repository.getDepartments().isEmpty()){
            log.info("No departments found");
            return;
        }
        reportAll();
        Department departmentForRemove=null;
        int id=0;
        try{
            id =Validator.getCorrectInt("department ID");
            departmentForRemove=Repository.findDepartmentById(id).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find department")
            );
        }
        catch (IllegalArgumentException e){
            log.warn("No department with ID {}", id);
            removeMenu();
        }
        if (departmentForRemove != null) {
            Repository.removeDepartment(departmentForRemove);
            log.info("Department with ID {} removed successfully", id);
        }
    }
    @Override
    public void searchMenu() {
        if(Repository.getDepartments().isEmpty()){
            log.info("You have no departments");
            return;
        }
        System.out.println("\u001B[34mSearch department by:\u001B[0m");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch (choice) {
            case 1:
                int id=Validator.getCorrectInt("department ID");
                Repository.findDepartmentById(id).ifPresentOrElse(
                        System.out::println, () -> log.warn("No department with ID {}", id));
                break;
            case 2:
                String name =Validator.getCorrectString("department name");
                Repository.findDeparmentByName(name).ifPresentOrElse(
                        System.out::println, () -> log.warn("No department with name {}", name));
                break;
            case 3:
            default:
                break;
        }
    }

    @Override
    public void reportMenu() {
        if(Repository.getDepartments().isEmpty()) {
            log.info("No departments found");
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
