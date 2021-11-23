package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Kolya", "Baskov", (byte) 36);
        userService.saveUser("Filya", "Kirkorov", (byte) 34);
        userService.saveUser("Sofa", "Rotaru", (byte) 30);
        userService.saveUser("Alla", "Pugacheva", (byte) 46);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
