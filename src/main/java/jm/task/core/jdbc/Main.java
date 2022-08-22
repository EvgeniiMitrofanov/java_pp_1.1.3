package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        System.out.println("Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует");
        userService.dropUsersTable();
        System.out.println("Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует");
        userService.createUsersTable();
        userService.cleanUsersTable();
        System.out.println("Очистка содержания таблицы");
        userService.saveUser("John", "Parker", (byte)24);
        userService.saveUser("John2", "Parker2", (byte)242);
        userService.saveUser("John3", "Parker3", (byte)243);
        userService.saveUser("John4", "Parker4", (byte)244);
        userService.removeUserById(2);
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());

    }
}
