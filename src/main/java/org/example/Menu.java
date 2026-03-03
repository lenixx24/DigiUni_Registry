package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static int userChoice;
    private static ConsoleService consoleService;
    private static final Logger log = LogManager.getLogger(Menu.class);
    private static User user;
    public static void authorizationMenu(){
        System.out.println("\n \u001B[1;97mWelcome to DigiUni! \u001B[0m");
        System.out.println("1. \u001B[32mLog in\u001B[0m ");
        System.out.println("2. Exit ");
        userChoice = checkedUserChoice(1,2);
        UserService userService= new UserService();
        switch (userChoice){
            case 1: userService.logIn(); break;
            case 2: default: System.out.println("Goodbye! "); System.exit(0);
        }
        startMenu();
    }
    public static void startMenu() {
        while(true){
        System.out.println("1. \u001B[32mCreate\u001B[0m ");
        System.out.println("2. \u001B[34mSearch\u001B[0m ");
        System.out.println("3. \u001B[33mUpdate\u001B[0m ");
        System.out.println("4. \u001B[31mRemove\u001B[0m");
        System.out.println("5. \u001B[35mReport\u001B[0m");
        System.out.println("6. \u001B[36mManage users\u001B[0m");
        System.out.println("7. Exit ");
        userChoice = checkedUserChoice(1, 7);
        switch (userChoice) {
            case 1:
                createMenu();
                break;
            case 2:
                searchMenu();
                break;
            case 3:
                updateMenu();
                break;
            case 4:
                removeMenu();
                break;
            case 5:
                reportMenu();
                break;
            case 6:
                manageUsersMenu();
                break;
            default:
                System.out.println("Goodbye! ");
                System.exit(0);
        }
    }
    }

    private static void manageUsersMenu() {
        if(!user.canManageUsers()){
            log.warn("User {} with role {} can not manage users", user.getUserName(), user.getRole());
            return;
        }

    }

    private static void removeMenu() {
        if(!user.canEditRegistryUnits()){
            log.warn("User {} with role {} can not remove", user.getUserName(), user.getRole());
            return;
        }
        printMenu("\u001B[31mRemove\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);
        switch(userChoice){
            case 1:
                consoleService = new FacultyService();
                break;
            case 2:
                consoleService = new DepartmentService();
                break;
            case 3:
                consoleService = new StudentService();
                break;
            case 4:
                consoleService = new TeacherService();
                break;
            case 5: default: return;
        }
        consoleService.removeMenu();
        removeMenu();
    }
    private static void updateMenu() {
        if(!user.canEditRegistryUnits()){
            log.warn("User {} with role {} can not update", user.getUserName(), user.getRole());
            return;
        }
        printMenu("\u001B[33mUpdate\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);
        switch(userChoice){
            case 1:
                consoleService = new FacultyService();
                break;

            case 2:
                consoleService = new DepartmentService();
                break;
            case 3:
                consoleService = new StudentService();
                break;
            case 4:
                consoleService = new TeacherService();
                break;
            case 5: default: return;
        }
        consoleService.updateMenu();
        updateMenu();
    }

    private static void searchMenu() {
        printMenu("\u001B[34mSearch\u001B[0m");
        userChoice = checkedUserChoice(1, 5);
        scanner = new Scanner(System.in);
        switch(userChoice) {
            case 1:
                consoleService=new FacultyService();
                break;
            case 2:
                consoleService=new DepartmentService();
                break;
            case 3:
                consoleService=new StudentService();
                break;
            case 4:
                consoleService=new TeacherService();
                break;
            case 5: default: return;
        }
        consoleService.searchMenu();
        searchMenu();
    }

    private static void reportMenu() {
        printMenu("\u001B[35mReport\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        scanner=new Scanner(System.in);
        switch(userChoice){
            case 1:
                consoleService = new FacultyService();
                break;
            case 2 :
                consoleService = new DepartmentService();
                break;
            case 3 :
               consoleService = new StudentService();
                break;
            case 4 :
               consoleService = new TeacherService();
                break;
           case 5: default: return;
        }
       consoleService.reportMenu();
        reportMenu();
    }


    private static void createMenu() {
        if(!user.canEditRegistryUnits()){
            log.warn("User {} with role {} can not create", user.getUserName(), user.getRole());
            return;
        }
        printMenu("\u001B[32mCreate\u001B[0m");
        userChoice= checkedUserChoice(1, 5);
        switch(userChoice){
            case 1:
                consoleService = new FacultyService();
                break;
            case 2:
                    consoleService= new DepartmentService();
                    break;
            case 3:
               consoleService = new StudentService();
                break;
            case 4:
               consoleService = new TeacherService();
                break;
            case 5: default: return;
        }
        consoleService.createMenu();
        createMenu();
    }

    private static void printMenu(String actionName){
        System.out.println(actionName+" menu");
        System.out.println("1. "+actionName+" faculty");
        System.out.println("2. "+actionName+" department");
        System.out.println("3. "+actionName+" student");
        System.out.println("4. "+actionName+" teacher");
        System.out.println("5. Back");
    }
    private static int checkedUserChoice(int min, int max){
        System.out.print("\nEnter number("+min+" - "+max+"):");
        int res;
        while(true) {
            try{
                scanner=new Scanner(System.in);
                res= scanner.nextInt();
                if(res>=min&&res<=max) break;
                else log.warn("Enter number {}-{}: ", min, max);
            }
            catch(InputMismatchException e){
                log.warn("Input must be an integer");
            }
        }
        return res;
    }
    public static void setUser(User user){
        Menu.user=user;
    }

}
