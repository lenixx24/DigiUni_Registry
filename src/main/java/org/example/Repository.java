package org.example;

public class Repository {

    private Student[] students = new Student[300];
    private int studentCount = 0;

    private Teacher[] teachers = new Teacher[100];
    private int teacherCount = 0;

    private Department[] departments = new Department[50];
    private int departmentCount = 0;

    private Faculty[] faculties = new Faculty[10];
    private int facultyCount = 0;

//------------------------------------ADD/REMOVE STUDENT

    public void addStudent(Student student) {
        if (studentCount >= students.length)
            throw new ArrayIndexOutOfBoundsException("Too many students");
        students[studentCount++] = student;
    }

    private void removeStudent(Student student) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i] == student) {
                students[i] = students[studentCount - 1];
                students[studentCount - 1] = null;
                studentCount--;
                return;
            }
        }
    }

//------------------------------------ADD/REMOVE TEACHER

    public void addTeacher(Teacher teacher) {
        if (teacherCount >= teachers.length)
            throw new ArrayIndexOutOfBoundsException("Too many teachers");
        teachers[teacherCount++] = teacher;
    }

    public void removeTeacher(Teacher teacher) {
        for (int i = 0; i < teacherCount; i++) {
            if (teachers[i] == teacher) {
                teachers[i] = teachers[teacherCount - 1];
                teachers[teacherCount - 1] = null;
                teacherCount--;
                return;
            }
        }
    }

//------------------------------------ADD/REMOVE DEPARTMENT

    public void addDepartment(Department department) {
        if (departmentCount >= departments.length)
            throw new ArrayIndexOutOfBoundsException("Too many faculties");
        departments[departmentCount++] = department;
    }
    public void removeDepartment(Department department) {
        for (int i = 0; i < departmentCount; i++) {
            if (departments[i] == department) {
                departments[i] = departments[departmentCount - 1];
                departments[departmentCount - 1] = null;
                departmentCount--;
                return;
            }
        }
    }

//------------------------------------ADD/REMOVE FACULTY

    public void addFaculty(Faculty faculty) {
        if (facultyCount >= faculties.length)
            throw new ArrayIndexOutOfBoundsException("Too many faculties");
        faculties[facultyCount++] = faculty;
    }

    public void removeFaculty(Faculty faculty) {
        for (int i = 0; i < facultyCount; i++) {
            if (faculties[i] == faculty) {
                faculties[i] = faculties[facultyCount - 1];
                faculties[facultyCount - 1] = null;
                facultyCount--;
                return;
            }
        }
    }

//------------------------------------GETTERS
    public Student[] getStudents() {
    Student[] result = new Student[studentCount];
    for (int i = 0; i < studentCount; i++) result[i] = students[i];
    return result;
    }
    public Teacher[] getTeachers() {
        Teacher[] result = new Teacher[teacherCount];
        for (int i = 0; i < teacherCount; i++) result[i] = teachers[i];
        return result;
    }
    public Department[] getDepartments() {
        Department[] result = new Department[departmentCount];
        for (int i = 0; i < departmentCount; i++) result[i] = departments[i];
        return result;
    }
    public Faculty[] getFaculties() {
        Faculty[] result = new Faculty[facultyCount];
        for (int i = 0; i < facultyCount; i++) result[i] = faculties[i];
        return result;
    }

}
