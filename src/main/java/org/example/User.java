package org.example;

public class User{
    private String login;
    private Password password;
    private String userName;
    private Role role;
    public User (String login, String password, String userName, Role role){
        this.login=login;
        this.password=new Password(password);
        this.userName=userName;
        this.role=role;
    }
    public User (String login, String password, String userName){
        this.login=login;
        this.password=new Password(password);
        this.userName=userName;
        this.role=Role.USER;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public boolean hasPassword(String password){
        if(this.password.equals(password))return true;
        return false;
    }

}
class Password {
    private String password;
    private final int key;
    Password (String password){
        this.password=password;
        key= Math.toIntExact(Math.round(Math.random() * 1000));
    }

    public boolean equals(String password) {
        return this.password.equals(password);
    }
}
