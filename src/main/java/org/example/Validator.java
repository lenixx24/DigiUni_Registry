package org.example;
import org.apache.logging.log4j.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Validator {
    private static Scanner sc=new Scanner(System.in);
    private static final Logger log = LogManager.getLogger(Validator.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static Department getCorrectDepartment(String txt) {
        while (true) {
            int id = getCorrectInt(txt);
            Optional<Department> dep = Repository.findDepartmentById(id);
            if (dep.isPresent()) {
                return dep.get();
            } else {
                log.warn("Department with ID {} not found", id);
            }
        }
    }
    public static void checkGroupBelongsToDepartment(String groupName, Department targetDept) {
        for (Student s : Repository.getStudents()) {
            if (s.getGroup().equals(groupName)) {
                if (s.getDepartment().getId() != targetDept.getId()) {
                    throw new IllegalArgumentException("Group " + groupName +
                            " already in department " + s.getDepartment().getName());
                }
            }
        }
    }

    public static String getUniqueFacultyName(String txt) {
        while (true) {
            String name = getCorrectString(txt);
            if (Repository.findFacultyByFullName(name).isEmpty()) {
                return name;
            } else {
                log.warn("Faculty '{}' already exists!", name);
            }
        }
    }

    public static String getUniqueDepartmentName(String txt) {
        while (true) {
            String name = getCorrectString(txt);
            if (Repository.findDeparmentByName(name).isEmpty()) {
                return name;
            } else {
                log.warn("Department '{}' already exists!", name);
            }
        }
    }


    public static LocalDate getCorrectDate(String txt) {
        while (true) {
            System.out.println("Type " + txt + " (format dd.MM.yyyy): ");
            String input = sc.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("Input cannot be empty");
            } else {
                try {
                    LocalDate date = LocalDate.parse(input, formatter);
                    LocalDate oldEnough = LocalDate.now().minusYears(10);
                    if (date.isAfter(oldEnough)) {
                        System.out.println("The person must be older");
                    } else {
                        return date;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Type format day.month.year (ex: 15.05.2000)");
                }
            }
        }}

    public static LocalDate getCorrectHDate(String txt) {
        while (true) {
            System.out.println("Type " + txt + " (format dd.MM.yyyy): ");
            String input = sc.nextLine();

            if (input == null || input.isEmpty()) {
                System.out.println("Input cannot be empty");
            } else {
                try {
                    LocalDate date = LocalDate.parse(input, formatter);

                } catch (DateTimeParseException e) {
                    System.out.println("Type format day.month.year (ex: 15.05.2000)");
                }
            }
        }}

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
        sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter "+txt+": ");
            try{
                res= sc.nextInt();
                sc.nextLine();
                return res;
            }
            catch (InputMismatchException e){
                log.warn("Input must be an integer");
                sc.nextLine();
            }
        }
    }

    public static int getCorrectFacultyID(String txt) {
        int res;
        while(true){
            res= Validator.getCorrectInt(txt);
             if(Repository.findFacultyById(res).isPresent()) log.warn("Faculty with ID {} already exists", res);
            else return res;
         }
    }
    public static int getCorrectDepartmentID(String txt) {
        int res;
        while(true){
            res= Validator.getCorrectInt(txt);
            if(Repository.findDepartmentById(res).isPresent()) log.warn("Department with ID {} already exists", res);
            else return res;
        }
    }
    public static int getCorrectTeacherID(String txt) {
        int res;
        while(true){
            res= Validator.getCorrectInt(txt);
             if(Repository.findTeacherById(res).isPresent()) log.warn("Teacher with ID {} already exists", res);
            else return res;
        }
    }
    public static int getCorrectStudentID(String txt) {
        int res;
        while(true){
            res= Validator.getCorrectInt(txt);
             if(Repository.findStudentById(res).isPresent()) log.warn("Student with ID {} already exists", res);
            else return res;
        }
    }
    public static Faculty getCorrectFaculty(String txt) {
        int id;
        while(true){
            id=Validator.getCorrectInt(txt);
             if(Repository.findFacultyById(id).isEmpty()) log.warn("No faculty with ID {}", id);
            else return Repository.findFacultyById(id).get();
        }
    }

    public static Teacher getCorrectTeacher(String txt) {
        int id;
        while(true){
            id=Validator.getCorrectInt(txt);
            if(Repository.findFacultyById(id).isEmpty()) log.warn("No teacher with ID {}", id);
            else return Repository.findTeacherById(id).get();
        }
    }

    public static String getCorrectPhoneNumber(String txt) {
        String  res;
        while(true){
            System.out.println("Enter "+txt+": ");
            res=sc.nextLine();
            if(isPhoneNumber(res)) return res;
            else log.warn("{} is not a phone number. Number must be in format +xxxxxxxxxxxx", res);
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
            else log.warn("Email must contain @");
        }
    }
    public static int checkedUserChoice(int min, int max){
        System.out.print("\nEnter number("+min+" - "+max+"):");
        int res;
        while(true) {
            try{
                res= sc.nextInt();
                sc.nextLine();
                if(res>=min&&res<=max) break;
                else log.warn("Enter number {}-{}: ", min, max);
            }
            catch(InputMismatchException e){
                log.warn("Input must be an integer");
                sc.nextLine();
            }
        }
        return res;
    }

    public static Role getCorrectRole(String txt) {
        String res;
        sc=new Scanner(System.in);
        while (true){
            System.out.println("Enter "+txt+": ");
            res=sc.nextLine();
            if(res.equalsIgnoreCase("USER")) return Role.USER;
            else if(res.equalsIgnoreCase("MANAGER")) return Role.MANAGER;
            else if(res.equalsIgnoreCase("ADMIN")) return Role.ADMIN;
            else if(res.equalsIgnoreCase("blocked")) return Role.BLOCKED;
            else log.warn("No role {} found", res.toUpperCase());
        }
    }

    public static String getCorrectLogin(String txt) {
        String res;
        while(true){
            res= Validator.getCorrectString(txt);
            if(Repository.findUserByLogin(res).isPresent()) log.warn("User with this login already exists");
            else return res;
        }
    }
}
