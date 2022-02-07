package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connection;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }
    @Override
    public void createUsersTable() {
        String Create_SQL = "CREATE TABLE if NOT EXISTS users(" +
                "id SERIAL," +
                "name VARCHAR (250) NOT NULL," +
                "lastName VARCHAR (250) NOT NULL," +
                "age SMALLINT);";

        try (Connection connect = Util.connection()) {
           Statement statement = connect.createStatement();
            statement.executeUpdate(Create_SQL);
            System.out.println("users 'table' ийгиликтуу тузулду. Азаматсыз!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        String Drop_SQL = "DROP TABLE if EXISTS users";
        try (Connection connnect = Util.connection()) {
            PreparedStatement statement = connnect.prepareStatement(Drop_SQL);
            statement.executeUpdate();
            System.out.println("Drop ийгиликтуу ишке ашты. Азаматсыз!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Connection connnect = Util.connection()) {
            PreparedStatement statement = connnect.prepareStatement("INSERT INTO users(name, lastName, age) VALUES (?,?,?);");
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setInt(3,age);
            statement.executeUpdate();
            System.out.println(name +" "+" ДатаБаза га кошулуу.");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        String Remove_SQL = "DELETE * FROM users WHERE id = ?;";
        try (Connection connect = Util.connection()) {
            PreparedStatement statement = connect.prepareStatement(Remove_SQL);
            statement.executeUpdate();
            System.out.println("removeUserById ийгиликтуу иштеди!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        String Get_All_SQL = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection connect = Util.connection()) {
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(Get_All_SQL);
            System.out.println("getAllUsers ийгиликтуу тузулду!!!");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(userList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public void cleanUsersTable() {
        String Clean_SQL = "TRUNCATE users";
        try(Connection connect = Util.connection()){
            PreparedStatement statement = connect.prepareStatement(Clean_SQL);
            statement.executeUpdate();
            System.out.println("Clean users table иштеди.");
    }catch (SQLException e){
        e.printStackTrace();
        }
    }
}