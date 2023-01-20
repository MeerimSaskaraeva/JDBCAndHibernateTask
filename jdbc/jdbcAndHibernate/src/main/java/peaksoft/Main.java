package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService=new UserServiceImpl();

        userService.createUsersTable();
        userService.dropUsersTable();
        userService.saveUser("Meerim","Saskaraeava",(byte) 39);
        userService.saveUser("Malika","Mamytova",(byte) 13);
        userService.saveUser("Emir","Mamytov",(byte) 5);
        userService.saveUser("Rustam","Mamytov",(byte) 17);
        userService.removeUserById(1L);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();

    }
}
