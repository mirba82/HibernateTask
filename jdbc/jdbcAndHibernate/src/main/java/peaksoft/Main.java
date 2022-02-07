package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

       userService.createUsersTable();

        User user1 = new User("venere","adybaeva",(byte)23);
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());

        User user2 =new User("kubanych","bakitay",(byte)29);
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());

        User user3 = new User("mirbe","abylov",(byte)30);
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());

        User user4 = new User("saparbek","oskonbaev",(byte)4);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        User user5 = new User("Kira","Kirova",(byte)35);
        userService.saveUser(user5.getName(),user5.getLastName(),user5.getAge());

        List<User> userList = userService.getAllUsers();
        for(User user: userList){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.connection().close();

    }

}
