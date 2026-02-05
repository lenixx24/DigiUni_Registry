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

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }

}
