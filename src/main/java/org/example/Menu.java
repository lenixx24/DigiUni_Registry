package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static int userChoice;
    public static void startMenu(){
        System.out.println("Welcome to DigiUni! ");
        System.out.println("1. \u001B[32mCreate\u001B[0m ");
        System.out.println("2. \u001B[34mSearch\u001B[0m ");
        System.out.println("3. \u001B[33mUpdate\u001B[0m ");
        System.out.println("4. \u001B[31mRemove\u001B[0m");
        System.out.println("5. Exit ");
        userChoice= checkedUserChoice(1, 5);
    switch(userChoice){
        case 1: createMenu(); break;
        case 2: searchMenu(); break;
        case 3: updateMenu(); break;
        case 4: removeMenu(); break;
        case 5: default: System.out.println("Goodbye! "); System.exit(0);
    }
    }
    private static void removeMenu() {
        printMenu("\u001B[31mRemove\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);
        switch(userChoice){
            case 1:
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
                Repository.removeFaculty(facultyForRemove);
                if (facultyForRemove != null) {
                    System.out.println("Faculty removed successfully");
                }
            break;
            case 2:
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
                Repository.removeDepartment(departmentForRemove);
                    if (departmentForRemove != null) {
                        System.out.println("Department removed successfully");
                    }
                break;
            case 3:
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
                break;
            case 4:
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
                break;
            case 5: default: startMenu();
        }
        removeMenu();
    }
    private static void updateMenu() {
        printMenu("\u001B[33mUpdate\u001B[0m");
    }

    private static void searchMenu() {
        printMenu("\u001B[34mSearch\u001B[0m");
            userChoice = checkedUserChoice(1, 5);
            scanner = new Scanner(System.in);
            switch(userChoice) {
                case 1:
                    System.out.print("Enter faculty ID: ");
                    Repository.findFacultyById(scanner.nextInt())
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Faculty not found"));
                    break;
                case 2:
                    System.out.print("Enter department ID: ");
                    Repository.findDepartmentById(scanner.nextInt())
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Department not found"));
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    Repository.findStudentById(scanner.nextInt())
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Student not found"));
                    break;
                case 4:
                    System.out.print("Enter teacher ID: ");
                    Repository.findTeacherById(scanner.nextInt())
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Teacher not found"));
                    break;
                case 5: startMenu(); return;
            }
            searchMenu();

    }

    private static void createMenu() {
        printMenu("\u001B[32mCreate\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        switch(userChoice){
            case 1:
                if(Repository.getStudents().length<1||Repository.getTeachers().length<1) {
                    System.out.println("You can't create Faculty without teachers or students");
                    createMenu();
                }
                Faculty newFaculty = new Faculty(Repository.Naukma,
                        Validator.getCorrectFacultyID("ID"),
                        Validator.getCorrectString("full name"),
                        Validator.getCorrectString("short name"),
                        Validator.getCorrectTeacher(" dean's ID"),
                        Validator.getCorrectPhoneNumber("phone number"),
                        Validator.getCorrectEmail("email address"));
                Repository.addFaculty(newFaculty);
                System.out.println(newFaculty.getShortName()+" created successfully");
                break;
            case 2:
                    if(Repository.getFaculties().length<1) {
                        System.out.println("You can't create Department without faculties");
                        createMenu();
                    }
                    Department newDepartment = new Department(
                            Validator.getCorrectDepartmentID("ID"),
                            Validator.getCorrectString("name"),
                            Validator.getCorrectFaculty("faculty ID"),
                            Validator.getCorrectTeacher(" head's ID"),
                            Validator.getCorrectString("office"));
                    Repository.addDepartment(newDepartment);
                    System.out.println(newDepartment.getName()+" created successfully");
                    break;
            case 3:
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
                        Validator.getCorrectString("stude form"),
                        Validator.getCorrectString("status")
                       );
                Repository.addStudent(newStudent);
                System.out.println(newStudent.getLastName()+" "+newStudent.getFirstName()+" "+ newStudent.getMiddleName() +" created successfully");
                break;
            case 4:
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

                break;
            case 5: default: startMenu();
        }
        createMenu();
    }

    private static void printMenu(String actionName){
        System.out.println(""+actionName+" menu");
        System.out.println("1. "+actionName+" faculty");
        System.out.println("2. "+actionName+" department");
        System.out.println("3. "+actionName+" student");
        System.out.println("4. "+actionName+" teacher");
        System.out.println("5. Back");
    }
    private static int checkedUserChoice(int min, int max){
        System.out.print("Enter number("+min+" - "+max+"):");
        int res=-1;
            while(true) {
                try{
                    scanner=new Scanner(System.in);
                    res= scanner.nextInt();
                    if(res>=min&&res<=max) break;
                    else System.out.println("Enter correct number");
                }
                catch(InputMismatchException e){
                    System.out.println("Enter number");
                }
            }
        return res;
    }
}
