package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {
    public static University Naukma= new University("National University of Kyiv-Mohyla Academy",
            "NaUKMA", "Kyiv", "Hryhoriya Skovorody St, 2");
    private static List<Student> students = new ArrayList<>(300);
    private static int studentCount = 0;

    private static List<Teacher> teachers = new ArrayList<>(100);
    private static int teacherCount = 0;

    private static List<Department> departments = new ArrayList<>(50);
    private static int departmentCount = 0;

    private static List<Faculty> faculties = new ArrayList<>(10);
    private static int facultyCount = 0;

//------------------------------------ADD/REMOVE STUDENT

    public static void addStudent(Student student) {
        students.add(student);
        studentCount++;
    }

    public static void removeStudent(Student student) {
        if(student==null) return;
        students.remove(student);
        studentCount--;
    }

//------------------------------------ADD/REMOVE TEACHER

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacherCount++;
    }

    public static void removeTeacher(Teacher teacher) {
        if(teacher==null) return;
        teachers.remove(teacher);
        teacherCount--;
    }

//------------------------------------ADD/REMOVE DEPARTMENT

    public static void addDepartment(Department department) {
        departments.add(department);
        departmentCount++;
    }
    public static void removeDepartment(Department department) {
        if(department==null) return;
        departments.remove(department);
        departmentCount--;
    }

//------------------------------------ADD/REMOVE FACULTY

    public static void addFaculty(Faculty faculty) {
        faculties.add(faculty);
        facultyCount++;
    }

    public static void removeFaculty(Faculty faculty) {
        if(faculty==null) return;
       faculties.remove(faculty);
       facultyCount--;
    }

//------------------------------------GETTERS
    public static List<Student> getStudents() {
    List<Student> result = new ArrayList<>(studentCount);
    for (int i = 0; i < studentCount; i++) result.add(students.get(i));
    return result;
    }
    public static List<Teacher> getTeachers() {
        List<Teacher> result = new ArrayList<>(teacherCount);
        for (int i = 0; i < teacherCount; i++) result.add(teachers.get(i));
        return result;
    }
    public static List<Department> getDepartments() {
        List<Department> result = new ArrayList<>(departmentCount);
        for (int i = 0; i < departmentCount; i++) result.add(departments.get(i));
        return result;
    }
    public static List<Faculty> getFaculties() {
        List<Faculty>  result = new ArrayList<>(facultyCount);
        for (int i = 0; i < facultyCount; i++) result.add(faculties.get(i));
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

    public static Optional<Student> findStudentByFullName(String name){
        for(Student stud: students){
            if(stud == null) return Optional.empty();
            if (stud.getFullName().equals(name)){
                return Optional.of(stud);
            }
        }
        return Optional.empty();
    }

    public static Optional<Teacher> findTeacherByFullName(String name){
        for(Teacher teach: teachers){
            if(teach == null) return Optional.empty();
            if (teach.getFullName().equals(name)){
                return Optional.of(teach);
            }
        }
        return Optional.empty();
    }


    public static Optional<Faculty> findFacultyByShortName(String name) {
        for(Faculty faculty: faculties){
            if(faculty == null) return Optional.empty();
            if (faculty.getShortName().equals(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    public static Optional<Department> findDeparmentByName(String name) {
        for(Department department: departments){
            if(department == null) return Optional.empty();
            if (department.getName().equals(name)){
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }
}
