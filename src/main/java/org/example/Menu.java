package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Entities.User;
import org.example.Services.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static int userChoice;
    private static ConsoleService consoleService;
    private static final Logger log = LogManager.getLogger(Menu.class);
    private static User user;
    public static final UserFileService userFileService=new UserFileService("data/users.json");
    public static void authorizationMenu(){
        userFileService.loadUsersFromFile();
        StudentFileService.loadAll();
        System.out.println("\n \u001B[1;97mWelcome to DigiUni! \u001B[0m");
        while(true) {
            System.out.println("1. \u001B[32mLog in\u001B[0m ");
            System.out.println("2. Exit ");
            userChoice = checkedUserChoice(1, 2);
            UserService userService = new UserService();
            switch (userChoice) {
                case 1:
                    userService.logIn();
                    startMenu();
                    break;
                case 2:
                default:
                    StudentFileService.saveAll();
                    userFileService.saveUsersToFile(Repository.getUsers());
                    System.out.println("Goodbye! ");
                    System.exit(0);
            }
        }

    }
    public static void startMenu() {
        while(true){
        System.out.println("1. \u001B[32mCreate\u001B[0m ");
        System.out.println("2. \u001B[34mSearch\u001B[0m ");
        System.out.println("3. \u001B[33mUpdate\u001B[0m ");
        System.out.println("4. \u001B[31mRemove\u001B[0m");
        System.out.println("5. \u001B[35mReport\u001B[0m");
        System.out.println("6. \u001B[36mManage users\u001B[0m");
        System.out.println("7. Back ");
        System.out.println("8. Exit ");
        userChoice = checkedUserChoice(1, 8);
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
            case 7: return;
            default:
                StudentFileService.saveAll();
                userFileService.saveUsersToFile(Repository.getUsers());
                System.out.println("Goodbye! ");
                System.exit(0);
        }
    }
    }

    private static void manageUsersMenu() {
        if(!user.canManageUsers()){
            log.warn("{} {} can not manage users", user.getRole(), user.getUserName());
            return;
        }
        System.out.println("\u001B[36mManage users\u001B[0m");
        System.out.println("1. Create user");
        System.out.println("2. Remove user");
        System.out.println("3. Change user rights");
        System.out.println("4. Back");
        userChoice = Validator.checkedUserChoice(1,4);
        UserService userService= new UserService();
      switch (userChoice){
          case 1: userService.create(); break;
          case 2: userService.remove(); break;
          case 3: userService.changeRole(); break;
          default: return;
      }
      manageUsersMenu();
    }

    private static void removeMenu() {
        if(!user.canEditRegistryEntities()){
            log.warn("{} {} can not remove entities", user.getRole(), user.getUserName());
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
        if(!user.canEditRegistryEntities()){
            log.warn("{} {} can not update entities", user.getRole(), user.getUserName());
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
        if(!user.canViewAndSearch()){
            log.warn("{} {} can not search entities", user.getRole(), user.getUserName());
            return;
        }
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
        if(!user.canViewAndSearch()){
            log.warn("{} {} can not view repository", user.getRole(), user.getUserName());
            return;
        }
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
        if(!user.canEditRegistryEntities()){
            log.warn("{} {} can not create entities", user.getRole(), user.getUserName());
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
    public static User getUser(){
        return Menu.user;
    }

}
