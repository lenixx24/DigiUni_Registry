package org.example;

public class University {
    public final String fullName;
    public final String shortName;
    public final String city;
    public final String address;
    private Faculty[] faculties=new Faculty[10];
    private int numberOfFaculties;
    public University(String fullName, String shortName, String city, String address) {
        this.fullName = fullName;
        this.shortName=shortName;
        this.city=city;
        this.address=address;
    }
    public void addFaculty(Faculty faculty){
        if(numberOfFaculties>=faculties.length){
            throw new ArrayIndexOutOfBoundsException("Too many faculties");
        }
        faculties[numberOfFaculties]=faculty;
        numberOfFaculties++;
    }
}
