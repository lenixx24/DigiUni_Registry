package org.example;
import java.util.Scanner;

public class StudentService implements ConsoleService{
    private Scanner scanner;

    @Override
    public void createMenu() {
        Student newStudent = new Student(
                Validator.getCorrectInt("ID"),
                Validator.getCorrectString("last name"),
                Validator.getCorrectString("first name"),
                Validator.getCorrectString("middle name"),
                Validator.getCorrectString("birth date"),
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
        System.out.println(newStudent.getLastName()+" "+newStudent.getFirstName()+" "+ newStudent.getMiddleName() +" created successfully");
    }

    @Override
    public void updateMenu() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
                        case 6 : default: updateMenu();
                    }
                    System.out.println("Student updated successfully");
                    System.out.println(student);
                },
                () -> System.out.println("Student not found")
        );

    }

    @Override
    public void removeMenu() {
        System.out.println("Enter student id: ");
        Student studentForRemove=null;
        try{
            studentForRemove=Repository.findStudentById(scanner.nextInt()).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find student")
            );
        }
        catch (IllegalArgumentException e){
            System.out.println("Can not find student");
            removeMenu();
            scanner.close();
        }
        Repository.removeStudent(studentForRemove);
        if (studentForRemove != null) {
            System.out.println("Student removed successfully");
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
                System.out.print("Enter student ID: ");
                Repository.findStudentById(scanner.nextInt())
                        .ifPresentOrElse(System.out::println, () -> System.out.println("Student not found"));
                break;
            case 2:
                System.out.print("Enter full name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                Repository.findStudentByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> System.out.println("Student not found"));
                break;
            case 3: default: searchMenu();
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
        if (!found) System.out.println("No students found");

    }
}
