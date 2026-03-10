package org.example;

import exceptions.DenialException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);
    public void logIn(){
        String login=null;
        User user;
        while(true) {
            try {
                login = Validator.getCorrectString("login");
                user = Repository.findUserByLogin(login).orElseThrow(IllegalArgumentException::new);
                break;
            } catch (IllegalArgumentException e) {
                log.warn("No user with login {} ", login);
            }
        }
        String password=null;
        for(int i=2; i>=0; i--){
            password = Validator.getCorrectString("password");
            if(user.hasPassword(password)){
                Menu.setUser(user);
                log.info("You logged in successfully. Welcome, {} {}!", user.getRole(), user.getUserName());
                return;
            }
            else {
                if(i==0) {
                    log.warn("To much tries! Access is blocked");
                    System.exit(0);
                }
                else log.warn("Incorrect password. {} tries left", i);
            }
        }
    }

    public void create() {
        User user;
        user = new User(Validator.getCorrectString("username"),
                Validator.getCorrectLogin("login"),
                Validator.getCorrectString("password"),
                Validator.getCorrectRole("role (user, manager, admin)"));
        Repository.addUser(user);
        log.info("{} {} created successfully", user.getRole(), user.getUserName());
    }

    public void remove() {
        if(Repository.getUsers().isEmpty()) {
            log.info("You have no users for remove");
            return;
        }
        reportAll();
        String login;
        User userForRemove=null;
        try {
            login=Validator.getCorrectString(" user login");
            userForRemove = Repository.findUserByLogin(login).orElseThrow(
                    () -> new IllegalArgumentException("Can not find user")
            );
        } catch (IllegalArgumentException e) {
            log.warn("No user with this login");
            remove();
        }
        if (userForRemove != null) {
            if (Menu.getUser().equals(userForRemove)) {
                throw new DenialException("You can not remove yourself");
            }
            else{
            Repository.removeUser(userForRemove);
            log.info("User {} removed successfully", userForRemove.getUserName());
            }
        }
    }

    private void reportAll() {
        System.out.println("\nUsers:\n");
        int index = 1;
        for (User u : Repository.getUsers()) {
            if (u != null)  System.out.println(index++ + ". " + u);
        }
    }

    public void changeRole() {
        if(Repository.getUsers().isEmpty()) {
            log.info("You have no users to edit");
            return;
        }
        reportAll();
        String login;
        User userForChange =null;
        try {
            login=Validator.getCorrectString(" user login");
            userForChange = Repository.findUserByLogin(login).orElseThrow(
                    () -> new IllegalArgumentException("Can not find user")
            );
        } catch (IllegalArgumentException e) {
            log.warn("No user with this login");
            changeRole();
        }
        if (userForChange != null) {
            if (Menu.getUser().equals(userForChange)) {
                throw new DenialException("You can not edit your own role");
            }
            else {
                Role role = Validator.getCorrectRole("role (user, manager, admin, blocked)");
                userForChange.setRole(role);
                log.info("{} {} changed successfully", userForChange.getRole(), userForChange.getUserName());
            }
        }
    }
}
