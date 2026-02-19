package org.example;
import java.util.Scanner;

public class TeacherService implements ConsoleService{
    private Scanner scanner;

    @Override
    public void createMenu() {
        Teacher newTeacher = new Teacher(
                Validator.getCorrectInt("ID"),
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
        System.out.println(newTeacher.getLastName()+" "+newTeacher.getFirstName()+" "+newTeacher.getMiddleName() +" created successfully");
    }

    @Override
    public void updateMenu() {
        System.out.print("Enter teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
                    System.out.println("Teacher updated successfully");
                    System.out.println(teacher);
                },
                () -> System.out.println("Teacher not found")
        );

    }

    @Override
    public void removeMenu() {
        System.out.println("Enter teacher id: ");
        Teacher teacherForRemove=null;
        try{
            teacherForRemove=Repository.findTeacherById(scanner.nextInt()).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find teacher")
            );
        }
        catch (IllegalArgumentException e){
            System.out.println("Can not find teacher");
            removeMenu();
            scanner.close();
        }
        Repository.removeTeacher(teacherForRemove);
        if (teacherForRemove != null) {
            System.out.println("Teacher removed successfully");
        }

    }

    @Override
    public void searchMenu() {
        System.out.println("\u001B[34mSearch teacher by:\u001B[0m");
        scanner=new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch(choice) {
            case 1:
                System.out.print("Enter teacher ID: ");
                Repository.findTeacherById(scanner.nextInt())
                        .ifPresentOrElse(System.out::println, () -> System.out.println("Teacher not found"));
                break;
            case 2:
                System.out.print("Enter full name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                Repository.findTeacherByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> System.out.println("Teacher not found"));
                break;
            case 3: default: searchMenu();
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
        if (!found) System.out.println("No teachers found");

    }


}
