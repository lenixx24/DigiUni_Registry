package org.example;

import java.util.Objects;

public class User{
    private final String login;
    private Password password;
    private String userName;
    private Role role;
    private boolean canViewAndSearch;
    private boolean canEditRegistryEntities;
    private boolean canManageUsers;
    public User (String userName, String login, String password, Role role){
        this.login=login;
        this.password=new Password(password);
        this.userName=userName;
        this.role=role;
        this.setAbilities();
    }
    public User (String login, String password, String userName){
        this.login=login;
        this.password=new Password(password);
        this.userName=userName;
        this.role=Role.USER;
        this.setAbilities();
    }
    private void setAbilities(){
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
