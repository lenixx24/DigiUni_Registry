package org.example.Main;

import org.example.Entities.*;

import java.util.*;

public class Repository {
    public static University Naukma= new University("National University of Kyiv-Mohyla Academy",
            "NaUKMA", "Kyiv", "Hryhoriya Skovorody St, 2");
    private static Map<Integer, Student> students = new HashMap<>();

    private static Map<Integer, Teacher> teachers = new HashMap<>();

   private static Map<Integer, Department> departments = new HashMap<>();
    private static Map<Integer, Faculty> faculties = new HashMap<>();
    private static List<User> users = new ArrayList<>(5);
    private static int userCount=0;
/* static{
     Repository.addUser(new User("User","user123", "12345",  Role.USER));
     Repository.addUser(new User("Manager","manager", "m@n@ger",  Role.MANAGER));
     Repository.addUser(new User("Admin","admin", "@dm1n",  Role.ADMIN));
 }*/
//------------------------------------ADD/REMOVE STUDENT

    public static void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public static void removeStudent(Student student) {
        if (student == null) return;
        if (student.getDepartment() != null) {
            student.getDepartment().getStudents().remove(student);
        }
        if (student.getGroupObject() != null) {
            student.getGroupObject().getStudents().remove(student);
        }
        if(student.getFaculty()!=null)
            student.getFaculty().getStudents().remove(student);
        students.remove(student.getId());
    }

//------------------------------------ADD/REMOVE TEACHER

    public static void addTeacher(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
    }

    public static void removeTeacher(Teacher teacher) {
        if(teacher==null) return;
        if(!teacher.getDepartments().isEmpty()){
            for(Department d: teacher.getDepartments()){
                if(d==null) continue;
                if(teacher.equals(d.getHead())) d.removeHead();
                if(d.getFaculty()!=null)
                    d.getFaculty().getTeachers().remove(teacher);
                d.removeTeacher(teacher);

            }
        }
        teachers.remove(teacher.getId());
    }

//------------------------------------ADD/REMOVE DEPARTMENT

    public static void addDepartment(Department department) {
        departments.put(department.getId(), department);
    }
    public static void removeDepartment(Department department) {
        if(department==null) return;
        List<Group> groupsToRemove=new ArrayList<>();
        for(Group g: groups) {
            if(g==null) continue;
           if( department.equals(g.getDepartment()))
               groupsToRemove.add(g);}
        for(Group g: groupsToRemove) Repository.removeGroup(g);
        if(department.getFaculty()!=null)
            department.getFaculty().removeDepartment(department);
        if(!department.getTeachers().isEmpty()){
            List<Teacher> teachersForRemove= new ArrayList<>();
            for(Teacher t: department.getTeachers())
                if(t!=null) teachersForRemove.add(t);
            for(Teacher t: teachersForRemove)
                t.removeDepartment(department);
        }

        departments.remove(department.getId());
    }

//------------------------------------ADD/REMOVE FACULTY

    public static void addFaculty(Faculty faculty) {
        faculties.put(faculty.getID(), faculty);
    }

    public static void removeFaculty(Faculty faculty) {
        if(faculty==null) return;
        if(!faculty.getDepartments().isEmpty()){
            for(Department d: faculty.getDepartments()){
                if(d!=null) d.removeFaculty();
            }
        }
       faculties.remove(faculty.getID());
    }

//------------------------------------GETTERS
    public static List<Student> getStudents() {
        return students.values().stream().toList();
    }
    public static List<Teacher> getTeachers() {
        return teachers.values().stream().toList();
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
        if(students.containsKey(id)) return Optional.of(students.get(id));
        return Optional.empty();
    }
    public static Optional<Teacher> findTeacherById(int id){
        if(teachers.containsKey(id)) return Optional.of(teachers.get(id));
        return Optional.empty();
    }

    public static Optional<Student> findStudentByFullName(String name){
        for(Student stud: students.values()){
            if(stud == null) return Optional.empty();
            if (stud.getFullName().equalsIgnoreCase(name)){
                return Optional.of(stud);
            }
        }
        return Optional.empty();
    }

    public static Optional<Teacher> findTeacherByFullName(String name){
        for(Teacher teach: teachers.values()){
            if(teach == null) return Optional.empty();
            if (teach.getFullName().equalsIgnoreCase(name)){
                return Optional.of(teach);
            }
        }
        return Optional.empty();
    }


    public static Optional<Faculty> findFacultyByShortName(String name) {
        for(Faculty faculty: faculties.values()){
            if(faculty == null) return Optional.empty();
            if (faculty.getShortName().equalsIgnoreCase(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }
    public static Optional<Faculty> findFacultyByFullName(String name) {
        for(Faculty faculty: faculties.values()){
            if(faculty == null) return Optional.empty();
            if (faculty.getFullName().equalsIgnoreCase(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    public static Optional<Department> findDepartmentByName(String name) {
        for(Department department: departments.values()){
            if(department == null) return Optional.empty();
            if (department.getName().equalsIgnoreCase(name)){
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
    public static Optional<User> findUserByUsername(String name){
        for(User user: users){
            if(user == null) return Optional.empty();
            if (user.getUserName().equalsIgnoreCase(name)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
   public static void addUser(User user){
        if(user!=null){
        users.add(user);
        user.setPermissions();
        userCount++;
        }
   }
    public static void removeUser(User user){
        if(user == null) return;
        if(users.remove(user))
          userCount--;
    }
    private static List<Group> groups = new ArrayList<>();

    public static void addGroup(Group group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
    }
    public static void removeGroup(Group group) {
        if(!group.getStudents().isEmpty()){
            for(Student s: group.getStudents()) s.removeGroup();
        }
        group.getStudents().clear();
        groups.remove(group);
    }
    public static Optional<Group> findGroupByName(String name) {
        return groups.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
