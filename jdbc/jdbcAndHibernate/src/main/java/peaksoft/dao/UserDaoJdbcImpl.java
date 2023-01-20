package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    Connection connection;
    public UserDaoJdbcImpl() {
        this.connection= Util.getConnection();

    }

    public void createUsersTable() {
        String query="create table if not exists users" +
                "(id serial primary key," +
                "name varchar ," +
                "last_name varchar," +
                "age smallint);";
        try(Statement statement = connection.createStatement();) {
            statement.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String query="drop table users;";
        try (Statement statement = connection.createStatement();){
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String query="insert into users(name,last_name,age)" +
                "values(?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println(name+" Successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeUserById(long id) {
        String query="delete from users where id=?;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<User> getAllUsers() {
        List<User> userList=new ArrayList<>();
        String query="select * from users;";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String query="truncate table users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}