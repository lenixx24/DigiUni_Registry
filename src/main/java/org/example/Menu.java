package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static int userChoice;
    public static void startMenu(){
        System.out.println("\n \u001B[1;97mWelcome to DigiUni! \u001B[0m");

        System.out.println("1. \u001B[32mCreate\u001B[0m ");
        System.out.println("2. \u001B[34mSearch\u001B[0m ");
        System.out.println("3. \u001B[33mUpdate\u001B[0m ");
        System.out.println("4. \u001B[31mRemove\u001B[0m");
        System.out.println("5. \u001B[35mReport\u001B[0m");
        System.out.println("6. Exit ");
        userChoice= checkedUserChoice(1, 6);
    switch(userChoice){
        case 1: createMenu(); break;
        case 2: searchMenu(); break;
        case 3: updateMenu(); break;
        case 4: removeMenu(); break;
        case 5: reportMenu(); break;
        case 6: default: System.out.println("Goodbye! "); System.exit(0);
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
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);
        switch(userChoice){
            case 1:
                updateFaculty();
                break;

            case 2:
                updateDepartment();
                break;
            case 3:
                updateStudent();
                break;
            case 4:
                updateTeacher();
                break;
            case 5: default: startMenu();
        }
        updateMenu();
    }
    private static void updateFaculty() {
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

                    int choice = checkedUserChoice(1, 4);
                    switch (choice) {
                        case 1: faculty.changeDean(Validator.getCorrectTeacher("teacher ID"));break;
                        case 2 : faculty.changePhoneNumber(Validator.getCorrectPhoneNumber("phone number"));break;
                        case 3 : faculty.changeEmailAddress(Validator.getCorrectEmail("email address"));break;
                        case 4 : default: updateMenu();
                    }
                    System.out.println("Faculty updated successfully");
                    System.out.println(faculty);
                },
                () -> System.out.println("Faculty not found")
        );
    }
    private static void updateDepartment() {
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

                    int choice = checkedUserChoice(1, 4);
                    switch (choice) {
                        case 1: department.changeFaculty(Validator.getCorrectFaculty("faculty ID"));break;
                        case 2 : department.changeHead(Validator.getCorrectTeacher("teacher ID")); break;
                        case 3 : department.changeOffice(Validator.getCorrectString("office")); break;
                        case 4 : default: updateMenu();
                    }
                    System.out.println("Department updated successfully");
                    System.out.println(department);
                },
                () -> System.out.println("Department not found")
        );
    }

    private static void updateStudent() {
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

                    int choice = checkedUserChoice(1, 6);
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

    private static void updateTeacher() {
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

                    int choice = checkedUserChoice(1, 5);
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
                searchStudent();
                break;
            case 4:
                searchTeacher();
                break;
            case 5: default: startMenu();
        }
        searchMenu();
    }

    private static void searchStudent(){
        System.out.println("\u001B[34mSearch student by:\u001B[0m");
        scanner=new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Back");
        int choice = checkedUserChoice(1, 3);
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
        searchStudent();
    }

    private static void searchTeacher(){
        System.out.println("\u001B[34mSearch teacher by:\u001B[0m");
        scanner=new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Back");
        int choice = checkedUserChoice(1, 3);
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
        searchTeacher();
    }

    private static void reportMenu() {
        printMenu("\u001B[35mReport\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);

        switch(userChoice){
            case 1: {
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
                break;
            }

            case 2 : {
                System.out.println("\nDepartments:\n");
                boolean found = false;
                for (Department d: Repository.getDepartments()) {
                    if (d!= null) {
                        System.out.println( d);
                        found = true;
                    }
                }
                if (!found) System.out.println("No departments found");
                break;
            }

            case 3 : {
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
                break;
            }

            case 4 : {
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
                break;
            }

           case 5: default: startMenu();
        }

        reportMenu();
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
                        Validator.getCorrectString("study form"),
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
        System.out.print("\nEnter number("+min+" - "+max+"):");
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
