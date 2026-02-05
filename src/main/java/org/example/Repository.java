package org.example;

import java.util.Optional;

public class Repository {

    private static Student[] students = new Student[300];
    private static int studentCount = 0;

    private static Teacher[] teachers = new Teacher[100];
    private static int teacherCount = 0;

    private static Department[] departments = new Department[50];
    private static int departmentCount = 0;

    private static Faculty[] faculties = new Faculty[10];
    private static int facultyCount = 0;

//------------------------------------ADD/REMOVE STUDENT

    public static void addStudent(Student student) {
        if (studentCount >= students.length)
            throw new ArrayIndexOutOfBoundsException("Too many students");
        students[studentCount++] = student;
    }

    public static void removeStudent(Student student) {
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

    public static void addTeacher(Teacher teacher) {
        if (teacherCount >= teachers.length)
            throw new ArrayIndexOutOfBoundsException("Too many teachers");
        teachers[teacherCount++] = teacher;
    }

    public static void removeTeacher(Teacher teacher) {
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

    public static void addDepartment(Department department) {
        if (departmentCount >= departments.length)
            throw new ArrayIndexOutOfBoundsException("Too many faculties");
        departments[departmentCount++] = department;
    }
    public static void removeDepartment(Department department) {
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

    public static void addFaculty(Faculty faculty) {
        if (facultyCount >= faculties.length)
            throw new ArrayIndexOutOfBoundsException("Too many faculties");
        faculties[facultyCount++] = faculty;
    }

    public static void removeFaculty(Faculty faculty) {
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
    public static Student[] getStudents() {
    Student[] result = new Student[studentCount];
    for (int i = 0; i < studentCount; i++) result[i] = students[i];
    return result;
    }
    public static Teacher[] getTeachers() {
        Teacher[] result = new Teacher[teacherCount];
        for (int i = 0; i < teacherCount; i++) result[i] = teachers[i];
        return result;
    }
    public static Department[] getDepartments() {
        Department[] result = new Department[departmentCount];
        for (int i = 0; i < departmentCount; i++) result[i] = departments[i];
        return result;
    }
    public static Faculty[] getFaculties() {
        Faculty[] result = new Faculty[facultyCount];
        for (int i = 0; i < facultyCount; i++) result[i] = faculties[i];
        return result;
    }

//-------------------------------------FIND-BY-ID METHODS
    public static Optional<Faculty> findFacultyById(int id){

        for(Faculty fac: faculties){
            if(fac==null) return Optional.empty();
            if (fac.getID()==id){
                return Optional.of(fac);
            }
        }
        return Optional.empty();
    }
    public static Optional<Department> findDepartmentById(int id){
        for(Department dep: departments){
            if(dep==null) return Optional.empty();
            if (dep.getId()==id){
                return Optional.of(dep);
            }
        }
        return Optional.empty();
    }
    public static Optional<Student> findStudentById(int id){
        for(Student stud: students){
            if(stud==null) return Optional.empty();
            if (stud.getId()==id){
                return Optional.of(stud);
            }
        }
        return Optional.empty();
    }
    public static Optional<Teacher> findTeacherById(int id){
            for(Teacher teach: teachers){
                if(teach==null) return Optional.empty();
                if (teach.getId()==id){
                    return Optional.of(teach);
                }
            }
            return Optional.empty();
    }


}
