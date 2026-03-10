package org.example;
import exceptions.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService implements ConsoleService{
    private Scanner scanner;
    private static final Logger log = LogManager.getLogger(StudentService.class);
    @Override
    public void createMenu() {
        String groupName = Validator.getCorrectString("group name");
        Optional<Group> existingGroup = Repository.findGroupByName(groupName);

        Group studentGroup;
        if (existingGroup.isPresent()) {
            studentGroup = existingGroup.get();
            Validator.checkGroupBelongsToDepartment(groupName, studentGroup.getDepartment());
            log.info("Group is found. Department: {}, Faculty: {}",
                    studentGroup.getDepartment().getName(),
                    studentGroup.getDepartment().getFaculty().getShortName());
        } else {
            System.out.println("New Group! Choose department:");
            Department dept = Validator.getCorrectDepartment("Department ID");
            studentGroup = new Group(groupName, dept);
            Repository.addGroup(studentGroup);
        }
        Student newStudent = new Student(
                Validator.getCorrectStudentID("ID"),
                Validator.getCorrectString("last name"),
                Validator.getCorrectString("first name"),
                Validator.getCorrectString("middle name"),
                Validator.getCorrectDate("birth date"),
                Validator.getCorrectEmail("email address"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectString("student ID"),
                Validator.getCorrectInt("course"),
                studentGroup,
                Validator.getCorrectInt("entry year"),
                Validator.getCorrectString("study form"),
                Validator.getCorrectString("status")
        );
        Repository.addStudent(newStudent);
        newStudent.getAge();
        log.info("Student {} created, Age: {} years, Group:{} , Faculty: {}",
                newStudent.getFullName(), newStudent.getAge(), newStudent.getGroup(),newStudent.getGroup().getDepartment().getFaculty().getShortName());
    }

    @Override
    public void updateMenu() {
        if(Repository.getStudents().isEmpty()) {
            System.out.println("You have no students for update");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("student ID");

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
                        case 2 : try{
                            student.changeCourse(Validator.getCorrectInt("course"));
                            break;
                        }
                        catch(ValidationException e){
                            log.warn("Course must be between 1 and 6");
                        }
                        case 3:
                            String groupName = Validator.getCorrectString("new group name");
                            Optional<Group> existingGroup = Repository.findGroupByName(groupName);

                            Group newGroup;
                            if (existingGroup.isPresent()) {
                                newGroup = existingGroup.get();
                            } else {
                                System.out.println("Group not found. Choose department for the new group:");
                                Department dept = Validator.getCorrectDepartment("department ID");
                                newGroup = new Group(groupName, dept);
                                Repository.addGroup(newGroup);
                            }
                            student.getGroup().getDepartment().removeStudent(student);

                            student.changeGroup(newGroup);

                            newGroup.getDepartment().addStudent(student);
                            break;
                        case 4 : student.changeStudyForm(Validator.getCorrectString("study form")); break;
                        case 5 : student.changeStatus(Validator.getCorrectString("status")); break;
                        case 6 : default: return;
                    }
                    log.info("Student with ID {} updated successfully", id);
                    System.out.println(student);
                },
                () -> log.warn("No student with ID {}", id)
        );

    }
    @Override
    public void removeMenu() {
        System.out.println("1. Remove Student");
        System.out.println("2. Remove Group");
        System.out.println("3. Back");

        int choice = Validator.checkedUserChoice(1, 3);

        switch (choice) {
            case 1:
                reportAll();

                 int   id=Validator.getCorrectInt("student id");

                Repository.findStudentById(id).ifPresentOrElse(
                        student -> {
                            if (student.getGroupObject() != null) {
                                student.getGroupObject().getStudents().remove(student);
                            }

                            Repository.removeStudent(student);

                            log.info("Student with ID {} removed successfully", id);
                        },
                        () ->  log.warn("No student with ID {}",id)
                );
                break;

            case 2:
                String groupName = Validator.getCorrectString("group name");
                Repository.findGroupByName(groupName).ifPresentOrElse(
                        group -> {
                            if (!group.getStudents().isEmpty()) {
                                log.error("Cannot remove group '{}' because it still has {} students!",
                                        groupName, group.getStudents().size());
                                System.out.println("Please remove or transfer students first");
                            } else {
                                Repository.removeGroup(group);
                                log.info("Group '{}' removed successfully", groupName);
                            }
                        },
                        () -> log.warn("Group '{}' not found", groupName)
                );
                break;

            default:
                break;
        }
    }

    @Override
    public void searchMenu() {
        System.out.println("\u001B[34mSearch student by:\u001B[0m");
        scanner=new Scanner(System.in);
        System.out.println("1. ID");
        System.out.println("2. Full name");
        System.out.println("3. Course");
        System.out.println("4. Group");
        System.out.println("5. Back");
        int choice = Validator.checkedUserChoice(1, 5);
        switch(choice) {
            case 1:
                int id = Validator.getCorrectInt("student ID");
                Repository.findStudentById(id)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No student with ID {}", id));
                break;
            case 2:
                String name = Validator.getCorrectString("full name");
                Repository.findStudentByFullName(name)
                        .ifPresentOrElse(System.out::println, () -> log.warn("No student with name {}", name));
                break;
            case 3:
                int course = Validator.getCorrectInt("course (1-6)");
                searchByCourse(course);
                break;
            case 4:
                String groupName = Validator.getCorrectString("group name");
                searchByGroup(groupName);
                break;
            case 5: default: break;
        }

    }

    @Override
    public void reportMenu() {
        System.out.println("1. All students ");
        System.out.println("2. All students by course");
        System.out.println("3. Students of faculty by alphabet");
        System.out.println("4. Students of department by alphabet");
        System.out.println("5. Students of department by course");
        System.out.println("6. Students of department and exact course (Regular and Abc)");
        System.out.println("7. Back");

        int choice = Validator.checkedUserChoice(1, 7);

        List<Student> allStudents = new ArrayList<>(Repository.getStudents());

        switch (choice) {
            case 1:
                reportAll();
                break;
            case 2:
                sortByCourse(allStudents);
                printList(allStudents);
                break;

            case 3:
                Faculty fac = Validator.getCorrectFaculty("Faculty ID");
                List<Student> facStudents = new ArrayList<>(fac.getStudents());
                sortByAlphabet(facStudents);
                printList(facStudents);
                break;

            case 4:
                Department dep = Validator.getCorrectDepartment("Department ID");
                List<Student> deptStudents = new ArrayList<>(dep.getStudents());
                sortByAlphabet(deptStudents);
                printList(deptStudents);
                break;

            case 5:
                Department dep2 = Validator.getCorrectDepartment("Department ID");
                List<Student> deptStudents2 = new ArrayList<>(dep2.getStudents());
                sortByCourse(deptStudents2);
                printList(deptStudents2);
                break;

            case 6:
                Department dep3 = Validator.getCorrectDepartment("Department ID");
                int course = Validator.getCorrectInt("course (1-6)");
                reportDeptCourseStudents(dep3, course);
                break;

            case 7: default: break;
        }
    }

    private void printList(List<Student> list) {
        for (Student s : list) {
            System.out.println(s);
        }
    }
    private void reportAll() {
        System.out.println("\nStudents:\n");
        int index = 1;
        for (Student s : Repository.getStudents()) {
            if (s != null)  System.out.println(index++ + ". " + s);

        }
    }
    private void sortByAlphabet(List<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getLastName().compareToIgnoreCase(list.get(j).getLastName()) > 0) {
                    Student temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    private void sortByCourse(List<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getCourse() > list.get(j).getCourse()) {
                    Student temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    private void reportDeptCourseStudents(Department dep, int course) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student s : dep.getStudents()) {
            if (s.getCourse() == course) {
                filteredStudents.add(s);
            }
        }

        if (filteredStudents.isEmpty()) {
            System.out.println("No students found on " + dep.getName() + " for course " + course);
            return;
        }

        System.out.println("\nRegular list (Course " + course + ", " + dep.getName() + ")");
        printList(filteredStudents);

        sortByAlphabet(filteredStudents);
        System.out.println("\nAbc list (Course " + course + ", " + dep.getName() + ")");
        printList(filteredStudents);
    }

    private void searchByCourse(int course) {
        boolean found = false;
        System.out.println("\nStudents on course " + course + ":");
        for (Student s : Repository.getStudents()) {
            if (s.getCourse() == course) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No students found on this course");
    }

    private void searchByGroup(String groupName) {
        boolean found = false;
        System.out.println("\nStudents in group " + groupName + ":");
        for (Student s : Repository.getStudents()) {
            if (s.getGroupName().equalsIgnoreCase(groupName)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No students found in group " + groupName);
    }
}
