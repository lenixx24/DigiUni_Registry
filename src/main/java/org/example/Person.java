package org.example;

public class Person {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final String birthDate;
    private String email;
    private String phone;

    public Person(int id, String lastName, String firstName, String middleName, String birthDate, String email, String phone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }

     public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
}
