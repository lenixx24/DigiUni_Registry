package org.example;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    private static Scanner sc=new Scanner(System.in);

    public static String getCorrectString(String txt){
        String res;
        while(true){
            System.out.println("Enter "+txt+": ");
           res= sc.nextLine();
           if(res!=null&&!res.equals("")) return res;
        }


    }
    public static int getCorrectInt(String txt) {
        int res;
        while(true){
            System.out.println("Enter "+txt+": ");
            try{
                res= sc.nextInt();
                sc.nextLine();
                return res;
            }
            catch (Exception e){
                System.out.println("Input must be an integer");
                sc.nextLine();
            }
        }
    }

    public static int getCorrectFacultyID(String txt) {
        int res;
        while(true){
            System.out.println("Enter "+txt+": ");
            res= sc.nextInt();
            sc.nextLine();
            if(res<=0) System.out.println("ID must be positive int");
            else if(Repository.findFacultyById(res).isPresent()) System.out.println("Faculty with this ID already exists");
            else return res;
         }
    }
    public static int getCorrectDepartmentID(String txt) {
        int res;
        while(true){
            System.out.println("Enter "+txt+": ");
            res= sc.nextInt();
            if(res<=0) System.out.println("ID must be positive int");
            else if(Repository.findDepartmentById(res).isPresent()) System.out.println("Department with this ID already exists");
            else return res;
        }
    }

    public static Faculty getCorrectFaculty(String txt) {
        int id;
        while(true){
            System.out.println("Enter "+txt+": ");
            id= sc.nextInt();
            if(id<=0) System.out.println("ID must be positive int");
            else if(Repository.findFacultyById(id).isEmpty()) System.out.println("No faculty with this ID");
            else return Repository.findFacultyById(id).get();
        }
    }

    public static Teacher getCorrectTeacher(String txt) {
        int id;
        while (true) {
            System.out.println("Enter " + txt + ": ");
            try {
                id = sc.nextInt();
                sc.nextLine();
                if (id <= 0) Repository.findTeacherByFullName(getCorrectString(txt))
                        .orElseThrow(() -> new IllegalArgumentException("No teacher with this ID"));
                else Repository.findTeacherById(id)
                        .orElseThrow(() -> new IllegalArgumentException("No teacher with this ID"));
                return Repository.findTeacherById(id).get();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                sc.nextLine();
            }
            catch (IllegalArgumentException e){
                System.out.println("No teacher with this ID");
                sc.nextLine();
            }
        }
    }

    public static String getCorrectPhoneNumber(String txt) {
        String  res;
        while(true){
            System.out.println("Enter "+txt+": ");
            res=sc.nextLine();
            if(isPhoneNumber(res)) return res;
            else System.out.println("Phone number must contain only numbers and +");
        }
    }

    private static boolean isPhoneNumber(String str) {
        if(str==null|| str.length()<10||str.length()>13) return false;
        if(str.charAt(0)!='+'&&!Character.isDigit(str.charAt(0))) return false;
        for(int i=1; i<str.length(); i++){
            if(!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

    public static String getCorrectEmail(String txt) {
        String res;
        sc=new Scanner(System.in);
        while (true){
            System.out.println("Enter "+txt+": ");
            res=sc.nextLine();
            if(res.contains("@")) return res;
            else System.out.println("Email must contain @");
        }
    }
    public static int checkedUserChoice(int min, int max){
        System.out.print("\nEnter number("+min+" - "+max+"):");
        int res;
        while(true) {
            try{
                sc=new Scanner(System.in);
                res= sc.nextInt();
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
