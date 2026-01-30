package org.example;

class Student extends Person{
    private String studentId;
    private int course;
    private String group;
    private final int entryYear;
    private String studyForm;
    private String status;

    public Student(int id, String lastName, String firstName, String middleName, String birthDate, String email, String phone, String studentId, int course, String group, int entryYear, String studyForm, String status) {
        super(id, lastName, firstName, middleName, birthDate, email, phone);
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.entryYear = entryYear;
        this.studyForm = studyForm;
        this.status = status;
    }

}
