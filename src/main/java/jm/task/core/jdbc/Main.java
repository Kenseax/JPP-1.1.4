package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Николай", "Задорожнов", (byte) 25);
        userService.saveUser("Игорь", "Круглов", (byte) 12);
        userService.saveUser("Анастасия", "Конева", (byte) 56);
        userService.saveUser("Артур", "Смекалов", (byte) 32);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
