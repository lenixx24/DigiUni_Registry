package org.example;

import java.util.*;

public class Repository {
    public static University Naukma= new University("National University of Kyiv-Mohyla Academy",
            "NaUKMA", "Kyiv", "Hryhoriya Skovorody St, 2");
    private static List<Student> students = new ArrayList<>(300);
    private static int studentCount = 0;

    private static List<Teacher> teachers = new ArrayList<>(100);
    private static int teacherCount = 0;

   private static Map<Integer, Department> departments = new HashMap<>();
    private static int departmentCount = 0;
    private static Map<Integer, Faculty> faculties = new HashMap<>();
    private static int facultyCount = 0;
    private static List<User> users = new ArrayList<>(5);
    private static int userCount=0;
 static{
     Repository.addUser(new User("User","user123", "12345",  Role.USER));
     Repository.addUser(new User("Manager","manager", "m@n@ger",  Role.MANAGER));
     Repository.addUser(new User("Admin","admin", "@dm1n",  Role.ADMIN));
 }
//------------------------------------ADD/REMOVE STUDENT

    public static void addStudent(Student student) {
        students.add(student);
        studentCount++;
    }

    public static void removeStudent(Student student) {
        if (student == null) return;
        if (student.getGroupObject() != null) {
            student.getGroupObject().getStudents().remove(student);
        }
        if (student.getDepartment() != null) {
            student.getDepartment().getStudents().remove(student);
        }

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
        departments.put(department.getId(), department);
        departmentCount++;
    }
    public static void removeDepartment(Department department) {
        if(department==null) return;
        departments.remove(department.getId());
        departmentCount--;
    }

//------------------------------------ADD/REMOVE FACULTY

    public static void addFaculty(Faculty faculty) {
        faculties.put(faculty.getID(), faculty);
        facultyCount++;
    }

    public static void removeFaculty(Faculty faculty) {
        if(faculty==null) return;
       faculties.remove(faculty.getID());
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
        return departments.values().stream().toList();
    }
    public static List<Faculty> getFaculties() {
        return faculties.values().stream().toList();
    }
    public static List<User> getUsers() {
        List<User>  result = new ArrayList<>(userCount);
        for (int i = 0; i < userCount; i++) result.add(users.get(i));
        return result;
    }

//-------------------------------------FIND-BY-ID METHODS
    public static Optional<Faculty> findFacultyById(int id){
        if(faculties.containsKey(id)) return Optional.of(faculties.get(id));
        return Optional.empty();
    }
    public static Optional<Department> findDepartmentById(int id){
            if (departments.containsKey(id))
                return Optional.of(departments.get(id));
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
        for(Faculty faculty: faculties.values()){
            if(faculty == null) return Optional.empty();
            if (faculty.getShortName().equals(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }
    public static Optional<Faculty> findFacultyByFullName(String name) {
        for(Faculty faculty: faculties.values()){
            if(faculty == null) return Optional.empty();
            if (faculty.getFullName().equals(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    public static Optional<Department> findDepartmentByName(String name) {
        for(Department department: departments.values()){
            if(department == null) return Optional.empty();
            if (department.getName().equals(name)){
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }
    //------------------------------------USER METHODS
   public static Optional<User> findUserByLogin(String login){
       for(User user: users){
           if(user == null) return Optional.empty();
           if (user.hasLogin(login)){
               return Optional.of(user);
           }
       }
       return Optional.empty();
   }
   public static void addUser(User user){
        users.add(user);
        userCount++;
   }
    public static void removeUser(User user){
        if(user == null) return;
        if(users.remove(user))
          userCount--;
    }
    private static List<Group> groups = new ArrayList<>();

    public static void addGroup(Group group) {
        groups.add(group);
    }
    public static void removeGroup(Group group) {
        groups.remove(group);
    }
    public static Optional<Group> findGroupByName(String name) {
        return groups.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst();
    }

}
