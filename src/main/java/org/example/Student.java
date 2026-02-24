package org.example;
import java.time.LocalDate;

public class Student extends Person {
    private String studentId;
    private int course;
    private String group;
    private final int entryYear;
    private String studyForm;
    private String status;

    public Student(int id, String lastName, String firstName, String middleName, LocalDate birthDate, String email, String phone, String studentId, int course, String group, int entryYear, String studyForm, String status) {
        super(id, lastName, firstName, middleName, birthDate, email, phone);
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        this.entryYear = entryYear;
        this.studyForm = studyForm;
        this.status = status;
    }

    public void changeCourse(int newCourse) {
        if (newCourse < 1 || newCourse > 6) {
            throw new IllegalArgumentException("Course must be between 1 and 6");
        }
        this.course = newCourse;
    }

    public void changeGroup(String newGroup) {
        if (newGroup == null || newGroup.isEmpty()) {
            throw new IllegalArgumentException("Group must not be empty");
        }
        this.group = newGroup;
    }
    public void changeStatus(String newStatus) {
        if (newStatus == null || newStatus.isEmpty()) {
            throw new IllegalArgumentException("Status must not be empty");
        }
        this.status = newStatus;
    }
    public void changeStudyForm(String newStudyForm) {
        if (newStudyForm == null || newStudyForm.isEmpty()) {
            throw new IllegalArgumentException("Study form must not be empty");
        }
        this.studyForm = newStudyForm;
    }
    public String changeStudentId(String newStudentId) {
        if (newStudentId == null || newStudentId.isEmpty()) {
            throw new IllegalArgumentException("Student ID must not be empty");
        }
        return this.studentId = newStudentId;
    }

    @Override
    public String toString() {
        return super.toString() + ", StudentId: " + studentId + ", Course: " + course + ", Group: " + group + ", Entry year: " + entryYear + ", Study form: " + studyForm + ", Status: " + status;
    }

}
