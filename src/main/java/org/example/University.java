package org.example;

import java.util.ArrayList;
import java.util.List;

public class University {
    public final String fullName;
    public final String shortName;
    public final String city;
    public final String address;
    private List<Faculty> faculties=new ArrayList<>();
    private int numberOfFaculties;
    public University(String fullName, String shortName, String city, String address) {
        this.fullName = fullName;
        this.shortName=shortName;
        this.city=city;
        this.address=address;
    }
    public void addFaculty(Faculty faculty){
        faculties.add(faculty);
        numberOfFaculties++;
    }
    public void removeFaculty(Faculty faculty){
            Repository.removeFaculty(faculty);
            faculties.remove(faculty);
            numberOfFaculties--;
    }
    public int getNumberOfFaculties() {
        return numberOfFaculties;
    }

    @Override
    public String toString() {
        return fullName+" ("+shortName+"), "+city+" city, "+address;
    }
}
