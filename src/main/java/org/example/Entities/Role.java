package org.example.Entities;

public enum Role {
    USER,
    MANAGER,
    ADMIN,
    BLOCKED;
    public static final int READ    = 1;      // 0001
    public static final int UPDATE   = 2;      // 0010
    public static final int DELETE  = 4;      // 0100
    public static final int MANAGE_USERS = 8;      // 1000
    public static final int ALL   = 15;     // 1111

}
