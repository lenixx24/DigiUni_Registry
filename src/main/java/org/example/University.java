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
    public void removeFaculty(Faculty faculty){
            for(int i=0; i<numberOfFaculties; i++){
                    if (faculties[i].equals(faculty)) {
                        for(int j=i; j<numberOfFaculties-1; j++)
                            faculties[j]=faculties[j+1];
                        faculties[numberOfFaculties-1]=null;
                        numberOfFaculties--;
                        return;
                    }
            }
    }
    public int getNumberOfFaculties() {
        return numberOfFaculties;
    }

    @Override
    public String toString() {
        return fullName+" ("+shortName+"), "+city+" city, "+address;
    }
}
