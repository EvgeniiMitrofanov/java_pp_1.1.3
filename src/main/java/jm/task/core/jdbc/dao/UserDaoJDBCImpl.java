package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl(){

    }
    private String url = "jdbc:mysql://localhost:3306/mysql";
    private String password = "кщще";
    private String user = "root";
    private final Connection connection;

    {
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, nameC VARCHAR(255), lastNameC VARCHAR(255), ageC INT)");
            System.out.println("Table Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
            System.out.println("Table Deleted Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            System.out.println("Database connected");
            PreparedStatement prepStatement = connection.prepareStatement("INSERT users (nameC, lastNameC, ageC) VALUES (?, ?, ?)");
            prepStatement.setString(1, name);
            prepStatement.setString(2, lastName);
            prepStatement.setByte(3, age);
            prepStatement.executeUpdate();
            System.out.println("Information Added Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            System.out.println("Database connected");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE Id = " + id + ";");
            System.out.println("Information Deleted Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            System.out.println("Database connected");
            Statement statement = connection.createStatement();
            ResultSet set = connection.createStatement().executeQuery("SELECT * FROM users");
            while (set.next()) {
                User user = new User(set.getString("nameC"), set.getString("lastNameC"), set.getByte("ageC"));
                user.setId(set.getLong("Id"));
                list.add(user);
            }
            System.out.println("Information Added To List Successfully");
        } catch (SQLException e) {
            System.err.println("Cannot connect ! ");
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE users");
            System.out.println("Data Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
