package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherService implements ConsoleService{
    private Scanner scanner;
    private static final Logger log = LogManager.getLogger(TeacherService.class);
    @Override
    public void createMenu() {
        Teacher newTeacher = new Teacher(
                Validator.getCorrectTeacherID("ID"),
                Validator.getCorrectString("last name"),
                Validator.getCorrectString("first name"),
                Validator.getCorrectString("middle name"),
                Validator.getCorrectDate("birth date"),
                Validator.getCorrectEmail("email address"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectString("job"),
                Validator.getCorrectString("academic degree"),
                Validator.getCorrectString("academic status"),
                Validator.getCorrectHDate("hire date"),
                Validator.getCorrectInt("workload")
        );

        System.out.println("How many departments does the teacher belong to?");
        int count = Validator.getCorrectInt("count");
        for (int i = 0; i < count; i++) {
            while (true) {
                System.out.println("Adding department " + (i + 1));
                Department dep = Validator.getCorrectDepartment("department ID");

                if (newTeacher.getDepartments().contains(dep)) {
                    log.warn("Teacher is already added to this department '{}'!", dep.getName());
                } else {
                    newTeacher.addDepartment(dep);
                    log.info("Teacher added to department: {} (Faculty: {})",
                            dep.getName(), dep.getFaculty().getShortName());
                    break;
                }
            }
        }
        Repository.addTeacher(newTeacher);
        newTeacher.getAge();
        log.info("Teacher {} created, Age: {} years",
                newTeacher.getFullName(), newTeacher.getAge());
    }


    @Override
    public void updateMenu() {
        if(Repository.getTeachers().isEmpty()) {
            System.out.println("You have no teachers for update");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("teacher ID");

        Repository.findTeacherById(id).ifPresentOrElse(
                teacher -> {
                    System.out.println("\nTeacher found:");
                    System.out.println(teacher);
                    System.out.println("\nUpdate teacher:");
                    System.out.println("1. Job");
                    System.out.println("2. Degree");
                    System.out.println("3. Academic status");
                    System.out.println("4. Workload");
                    System.out.println("5. Add to a new Department");
                    System.out.println("6. Remove from a Department");
                    System.out.println("7. Back");

                    int choice = Validator.checkedUserChoice(1, 7);
                    switch (choice) {
                        case 1: teacher.changeJob(Validator.getCorrectString("job")); break;
                        case 2 : teacher.changeDegree(Validator.getCorrectString("degree")); break;
                        case 3 : teacher.changeAcademicStatus(Validator.getCorrectString("academic status")); break;
                        case 4 : teacher.changeWorkload(Validator.getCorrectInt("workload")); break;
                        case 5:
                            Department depToAdd = Validator.getCorrectDepartment("department ID to add");
                            if (teacher.getDepartments().contains(depToAdd)) {
                                log.warn("Teacher is already added to this department!");
                            } else {
                                teacher.addDepartment(depToAdd);
                                log.info("Added to: {}", depToAdd.getName());
                            }
                            break;
                        case 6:
                            if (teacher.getDepartments().size() <= 1) {
                                log.error("Teacher must have at least one department!");
                            } else {
                                System.out.println("Current departments:");
                                teacher.getDepartments().forEach(d -> System.out.println(d.getId() + ": " + d.getName()));
                                int depId = Validator.getCorrectInt("department ID to remove");

                                teacher.getDepartments().stream()
                                        .filter(d -> d.getId() == depId)
                                        .findFirst()
                                        .ifPresentOrElse(
                                                d -> {
                                                    teacher.removeDepartment(d);
                                                    log.info("Teacher is removed from {}", d.getName());
                                                },
                                                () -> log.warn("Teacher isn't in department with ID {}", depId)
                                        );
                            }break;
                        case 7 : default: return;
                    }
                    log.info("Teacher with ID {} updated successfully", id);
                    System.out.println(teacher);
                },
                () -> log.warn("No teacher with ID {}", id)
        );

    }

    @Override
    public void removeMenu() {
        if(Repository.getTeachers().isEmpty()) {
            System.out.println("You have no teachers for remove");
            return;
        }
        reportAll();
        scanner = new Scanner(System.in);
        Teacher teacherForRemove=null;
        int id=Validator.getCorrectInt("teacher ID");
        try{
            teacherForRemove=Repository.findTeacherById(id).orElseThrow(
                    ()-> new IllegalArgumentException("Can not find teacher")
            );
        }
        catch (IllegalArgumentException e){
            log.warn("No teacher with ID {}", id);
            removeMenu();
        }
        Repository.removeTeacher(teacherForRemove);
        if (teacherForRemove != null) {
            log.info("Teacher with ID {} removed successfully", id);
        }

    }

    @Override
    public void searchMenu() {
        System.out.println("\u001B[34mSearch teacher by:\u001B[0m");
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Department");
        System.out.println("4. Back");
        int choice = Validator.checkedUserChoice(1, 4);
        switch(choice) {
            case 1:
                int id = Validator.getCorrectInt("teacher ID");
                Repository.findTeacherById(id)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No teacher with ID {}", id));
                break;
            case 2:
                String name = Validator.getCorrectString("full name");
                Repository.findTeacherByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No teacher with name {}", name));
                break;
            case 3:
                Department dep = Validator.getCorrectDepartment("department ID");
                searchByDepartment(dep);
            case 4: default: break;
        }

    }

    @Override
    public void reportMenu() {
        System.out.println("1. All teachers ");
        System.out.println("2. All teachers by department");
        System.out.println("3. Teachers of faculty by alphabet");
        System.out.println("4. Teachers of department by alphabet");
        System.out.println("5. Back");

        int choice = Validator.checkedUserChoice(1, 5);
        if (choice == 5) return;

        switch (choice) {
            case 1:
                reportAll();
                break;
            case 2:
                List<Teacher> all = new ArrayList<>(Repository.getTeachers());
                sortByAlphabet(all);
                printList(all);
                break;

            case 3:
                Faculty fac = Validator.getCorrectFaculty("Faculty ID");
                List<Teacher> facTeachers = new ArrayList<>(fac.getTeachers());
                sortByAlphabet(facTeachers);
                printList(facTeachers);
                break;
            case 4:
                Department dep = Validator.getCorrectDepartment("Department ID");
                List<Teacher> deptTeachers = new ArrayList<>(dep.getTeachers());
                sortByAlphabet(deptTeachers);
                printList(deptTeachers);
                break;
                case 5: default: break;
        }
    }
    private void printList(List<Teacher> list) {
        if (list.isEmpty()) System.out.println("No teachers found");
        for (Teacher t : list) {
            System.out.println(t);
        }
    }

    private void reportAll() {
        System.out.println("\nTeachers:\n");
        int index = 1;
        for (Teacher t : Repository.getTeachers()) {
            if (t != null)  System.out.println(index++ + ". " + t);

        }
    }

    private void sortByAlphabet(List<Teacher> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getLastName().compareToIgnoreCase(list.get(j).getLastName()) > 0) {
                    Teacher temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    private void searchByDepartment(Department targetDep) {
        System.out.println("\nTeachers in department: " + targetDep.getName());
        boolean found = false;

        for (Teacher t : Repository.getTeachers()) {
            if (t.getDepartments().contains(targetDep)) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No teachers in department");
        }
    }

}
