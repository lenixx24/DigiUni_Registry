package org.example.Services;

public sealed interface ConsoleService permits StudentService, TeacherService,
        FacultyService, DepartmentService {
    public void createMenu();
    public void updateMenu();
    public void removeMenu();
    public void searchMenu();
    public void reportMenu();
}
