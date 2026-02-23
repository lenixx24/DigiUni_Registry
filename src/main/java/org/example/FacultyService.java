package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FacultyService implements ConsoleService {
    private static final Logger log = LogManager.getLogger(FacultyService.class);
    @Override
    public void createMenu() {
        Faculty newFaculty = new Faculty(Repository.Naukma,
                Validator.getCorrectFacultyID("ID"),
                Validator.getCorrectString("full name"),
                Validator.getCorrectString("short name"),
                Validator.getCorrectPhoneNumber("phone number"),
                Validator.getCorrectEmail("email address"));
        Repository.addFaculty(newFaculty);
        Repository.Naukma.addFaculty(newFaculty);
        log.info("{} created successfully", newFaculty.getShortName());
    }

    @Override
    public void updateMenu() {
        if(Repository.getFaculties().isEmpty()) {
            log.info("You have no faculties for update");
            return;
        }
        reportAll();
        int id = Validator.getCorrectInt("faculty ID");
        Repository.findFacultyById(id).ifPresentOrElse(
                faculty -> {
                    System.out.println("\nFaculty found:");
                    System.out.println(faculty);
                    System.out.println("\nUpdate faculty:");
                    System.out.println("1. Dean");
                    System.out.println("2. Phone number");
                    System.out.println("3. Email address");
                    System.out.println("4. Back");

                    int choice = Validator.checkedUserChoice(1, 4);
                    switch (choice) {
                        case 1:
                            if(Repository.getTeachers().isEmpty()) {
                                log.warn("No teachers found");
                                break;
                            }
                            faculty.changeDean(Validator.getCorrectTeacher("teacher ID"));
                            break;
                        case 2:
                            faculty.changePhoneNumber(Validator.getCorrectPhoneNumber("phone number"));
                            break;
                        case 3:
                            faculty.changeEmailAddress(Validator.getCorrectEmail("email address"));
                            break;
                        case 4:
                        default:
                            return;
                    }
                    log.info("Faculty with ID {} updated successfully", id);
                    System.out.println(faculty);
                },
                () -> log.warn("No faculty with ID {}", id)
        );
    }

    @Override
    public void removeMenu() {
        if(Repository.getFaculties().isEmpty()) {
            log.info("You have no faculties for remove");
            return;
        }
        reportAll();
        Faculty facultyForRemove = null;
        int id=0;
        try {
            id=Validator.getCorrectInt(" faculty id");
            facultyForRemove = Repository.findFacultyById(id).orElseThrow(
                    () -> new IllegalArgumentException("Can not find faculty")
            );
        } catch (IllegalArgumentException e) {
            log.warn("No faculty with ID {}", id);
            removeMenu();
        }
        if (facultyForRemove != null) {
            Repository.removeFaculty(facultyForRemove);
            log.info("Faculty with ID {} removed successfully", id);
        }
    }

    @Override
    public void searchMenu() {
        if(Repository.getFaculties().isEmpty()) {
            log.info("You have no faculties");
            return;
        }
        System.out.println("\u001B[34mSearch faculty by:\u001B[0m");
        System.out.println("1. ID");
        System.out.println("2. Short name");
        System.out.println("3. Back");
        int choice = Validator.checkedUserChoice(1, 3);
        switch (choice) {
            case 1:
                int id=Validator.getCorrectInt("faculty ID");
                Repository.findFacultyById(id).ifPresentOrElse(
                        System.out::println, () -> log.warn("No faculty with ID {}", id));
                break;
            case 2:
                String name=Validator.getCorrectString("faculty short name");
                Repository.findFacultyByShortName(name).ifPresentOrElse(
                        System.out::println, () -> log.warn("No faculty with name {}", name));
                break;
            case 3:
            default:
                break;
        }

    }

    @Override
    public void reportMenu() {
        if(Repository.getFaculties().isEmpty()){
            log.info("No faculties found");
            return;
        }
        reportAll();
    }

    private void reportAll() {
        System.out.println("\nFaculties:\n");
        int index = 1;
        for (Faculty f : Repository.getFaculties()) {
            if (f != null)  System.out.println(index++ + ". " + f);

        }
    }
}