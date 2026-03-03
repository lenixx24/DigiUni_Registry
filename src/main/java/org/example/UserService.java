package org.example;

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
                log.info("You logged in successfully. Welcome, {}!", user.getUserName());
                return;
            }
            else {
                if(i==0) log.warn("To much tries! Access is blocked");
                else log.warn("Incorrect password. {} tries left", i);
            }
        }
    }
}
