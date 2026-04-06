package org.example.Entities;

import org.example.Annotations.Encrypted;
import org.example.Annotations.UserJsonField;

import java.util.Objects;

public class User{
    @UserJsonField
    private String login;
    @Encrypted
    @UserJsonField
    private String password;
    @UserJsonField
    private String userName;
    @UserJsonField
    private Role role;
    private int permissions;

    public User(){}
    public User (String userName, String login, String password, Role role){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=role;
        this.setPermissions();
    }
    public User (String login, String password, String userName){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=Role.USER;
        this.setPermissions();
    }
    public void setPermissions() {
        switch(this.role){
            case USER:
                addPermission(Role.READ); break;
            case MANAGER:
                addPermission(Role.READ);
                addPermission(Role.UPDATE);
                addPermission(Role.DELETE);
            break;
            case ADMIN:
                addPermission(Role.ALL); break;
            case BLOCKED:
                removePermission(Role.ALL); break;

        }
    }
    public void addPermission(int p){
        this.permissions|=p;
    }
    public void removePermission(int p) {
        this.permissions &= ~p;
    }

    public boolean hasPermission(int p) {
        return (this.permissions & p) == p;
    }
    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }
   public void setRole(Role role){
        this.role=role;
        setPermissions();
   }

    public boolean hasPassword(String password){
        return this.password.equals(password);
    }

    public boolean hasLogin(String login) {
        return this.login.equals(login);
    }

    @Override
    public String toString() {
        return role+" "+userName+", login: "+login;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof User cmpUser)) return false;
        if(!userName.equals(cmpUser.getUserName())) return false;
        if(!cmpUser.hasLogin(login)) return false;
        return Objects.equals(login, cmpUser.login)&&Objects.equals(userName, cmpUser.userName);

    }
    @Override
    public int hashCode() {
        return Objects.hash(login, userName);
    }
}
