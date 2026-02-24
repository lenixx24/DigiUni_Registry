package org.example;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final LocalDate birthDate;
    private String email;
    private String phone;

    public Person(int id, String lastName, String firstName, String middleName, LocalDate birthDate, String email, String phone) {
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

    public int getAge() {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "ID: "+id+", "+lastName + " " + firstName + " " + middleName + ", Age: "+getAge();
    }

     public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
}
