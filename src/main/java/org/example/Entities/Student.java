package org.example.Entities;
import exceptions.ValidationException;

import java.time.LocalDate;
import java.util.Objects;

public class Student extends Person {
    private String studentId;
    private int course;
    private final int entryYear;
    private String studyForm;
    private String status;
    private Department department;
    private Group group;

    public void setDepartment(Department department) {
        this.department = department;
        if (department != null) {
            department.addStudent(this);
        }
    }

    public Department getDepartment() {
        return department;
    }


    public Student(int id, String lastName, String firstName, String middleName, LocalDate birthDate, String email, String phone, String studentId, int course,Group group, int entryYear, String studyForm, String status) {
        super(id, lastName, firstName, middleName, birthDate, email, phone);
        this.studentId = studentId;
        this.course = course;
        this.group = group;
        if(group!=null){
             this.department = group.getDepartment();
                group.addStudent(this);
        }
        if (this.department != null) {
            this.department.addStudent(this);
        }
        this.entryYear = entryYear;
        this.studyForm = studyForm;
        this.status = status;
    }
    public Group getGroupObject() { return group; }
    public String getGroupName() {
        if(group==null) return "";
        return group.getName(); }

    public Faculty getFaculty() {
        if(group!=null&&group.getDepartment()!=null)
            return group.getDepartment().getFaculty();
        return null;
    }
    public void changeCourse(int newCourse) {
        if (newCourse < 1 || newCourse > 6) {
            throw new ValidationException("Course must be between 1 and 6");
        }
        this.course = newCourse;
    }
    public int getCourse() {
        return course;
    }

    public void changeGroup(Group newGroup) {
        if (newGroup == null) {
            throw new ValidationException("Group must not be null");
        }
        this.group = newGroup;
    }
    public Group getGroup() {
        return group;
    }
    public int getEntryYear() {
        return entryYear;
    }
    public String getStudyForm() {
        return studyForm;
    }
    public String getStatus() {
        return status;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getBirthDate(){
        return birthDate.toString();
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getFacultyName(){
        if(getFaculty()!=null)
            return getFaculty().getShortName();
        return "none";
    }
    public void changeStatus(String newStatus) {
        if (newStatus == null || newStatus.isEmpty()) {
            throw new ValidationException("Status must not be empty");
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
            throw new ValidationException("Student ID must not be empty");
        }
        return this.studentId = newStudentId;
    }

    @Override
    public String toString() {
        String faculty = getFacultyName();
        return super.toString() + ", StudentId: " + studentId + ", Course: " + course + ", Group: " + group + ", Faculty: " + faculty + ", Entry year: " + entryYear + ", Study form: " + studyForm + ", Status: " + status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId);
    }

    public void removeGroup() {
        this.group=null;
    }
}
