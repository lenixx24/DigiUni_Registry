package org.example;

public class User{
    private String login;
    private String password;
    private String userName;
    private Role role;
    public User (String login, String password, String userName, Role role){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=role;
    }
    public User (String login, String password, String userName){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=Role.USER;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }
}
