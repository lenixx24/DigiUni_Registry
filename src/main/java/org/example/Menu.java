package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private static int userChoice;
    public static void startMenu(){
        System.out.println("Welcome to DigiUni! ");
        System.out.println("1. Create ");
        System.out.println("2. Search ");
        System.out.println("3. Update ");
        System.out.println("4. Delete ");
        System.out.println("5. Exit ");
        userChoice= checkedUserChoice(1, 5);
    switch(userChoice){
        case 1: createMenu(); break;
        case 2: searchMenu(); break;
        case 3: updateMenu(); break;
        case 4: deleteMenu(); break;
        case 5: default: System.out.println("Goodbye! "); System.exit(0);
    }
    }
    private static void deleteMenu() {
        printMenu("Delete");
        userChoice= checkedUserChoice(1, 5);
        switch(userChoice){
            case 1:  break;
            case 2:  break;
            case 3:  break;
            case 4:  break;
            case 5: default: startMenu();
        }
    }

    private static void updateMenu() {
        printMenu("Update");
    }

    private static void searchMenu() {
        printMenu("Search");
    }

    private static void createMenu() {
        printMenu("Create");
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
