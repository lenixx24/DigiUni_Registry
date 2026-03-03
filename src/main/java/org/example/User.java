package org.example;

public class User{
    private final String login;
    private Password password;
    private String userName;
    private Role role;
    private boolean canEditRegistryUnits;
    private boolean canManageUsers;
    public User (String login, String password, String userName, Role role){
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
                canEditRegistryUnits=false;
                canManageUsers=false;
            }
            case MANAGER -> {
                canEditRegistryUnits=true;
                canManageUsers=false;
            }
            case ADMIN ->{
                canManageUsers=true;
                canEditRegistryUnits=true;
            }
        }
    }
    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }

    public boolean canEditRegistryUnits() {
        return canEditRegistryUnits;
    }

    public boolean canManageUsers() {
        return canManageUsers;
    }

    public boolean hasPassword(String password){
        if(this.password.equals(password))return true;
        return false;
    }

    public boolean hasLogin(String login) {
        if(this.login.equals(login)) return true;
        else return false;
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
