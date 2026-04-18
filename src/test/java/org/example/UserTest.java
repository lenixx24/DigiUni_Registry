package org.example;

import org.example.Entities.Role;
import org.example.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;
    @Test
    public void testRolePermissions(){
        user.removePermission(Role.ALL);
        assertFalse(user.hasPermission(Role.MANAGE_USERS));
       user.addPermission(Role.READ);
       assertEquals(Role.READ, user.getPermissions());
    }
    @BeforeEach
    public void setUp(){
        user = new User("User","user", "12345",  Role.ADMIN);
    }

}
