package org.example;

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
    private boolean canViewAndSearch;
    private boolean canEditRegistryEntities;
    private boolean canManageUsers;
    public User(){}
    public User (String userName, String login, String password, Role role){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=role;
        this.setAbilities();
    }
    public User (String login, String password, String userName){
        this.login=login;
        this.password=password;
        this.userName=userName;
        this.role=Role.USER;
        this.setAbilities();
    }
    public void setAbilities(){
        switch(this.role){
            case USER -> {
                canViewAndSearch =true;
                canEditRegistryEntities =false;
                canManageUsers=false;
            }
            case MANAGER -> {
                canViewAndSearch =true;
                canEditRegistryEntities =true;
                canManageUsers=false;
            }
            case ADMIN ->{
                canManageUsers=true;
                canEditRegistryEntities =true;
                canViewAndSearch =true;
            }
            case BLOCKED -> {
                canEditRegistryEntities =false;
                canManageUsers=false;
                canViewAndSearch =false;
            }
        }
    }
    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }
   public void setRole(Role role){
        this.role=role;
   }
    public boolean canEditRegistryEntities() {
        return canEditRegistryEntities;
    }

    public boolean canManageUsers() {
        return canManageUsers;
    }

    public boolean canViewAndSearch() {
        return canViewAndSearch;
    }

    public boolean hasPassword(String password){
        if(this.password.equals(password))return true;
        return false;
    }

    public boolean hasLogin(String login) {
        if(this.login.equals(login)) return true;
        else return false;
    }

    @Override
    public String toString() {
        return role+" "+userName+", login: "+login;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(!(obj instanceof User)) return false;
        User cmpUser = (User) obj;
        if(!userName.equals(cmpUser.getUserName())) return false;
        if(!cmpUser.hasLogin(login)) return false;
        return Objects.equals(login, cmpUser.login)&&Objects.equals(userName, cmpUser.userName);

    }
    @Override
    public int hashCode() {
        return Objects.hash(login, userName);
    }
}
