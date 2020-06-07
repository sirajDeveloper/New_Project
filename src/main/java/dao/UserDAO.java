package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/user_table?useSSL=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "ергпьфт";

    private static final String INSERT_USER = "insert into user (name, email) values (?, ?)";
    private static final String SELECT_USER_BY_ID = "select * from user where id = ?";
    private static final String SELECT_USER = "select * from user where name = ?, email = ?";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String DELETE_USERS = "delete from user where id = ?";
    private static final String UPDATE_USERS = "update user set name = ?, email = ? where id = ?";

    private static Connection getMysqlConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public UserDAO() {}

    public List<User> getAllUsersDAO() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public boolean addUserDAO(User user) {
        int res = 0;
        try (Connection connection = getMysqlConnection();
             PreparedStatement statement =
                     connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            res = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res > 0;
    }

    public boolean deleteUserDAO(int id) {
        int res = 0;
        try (Connection connection = getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS)) {
            statement.setInt(1, id);
            res = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res > 0;
    }

    public boolean updateUserDAO(User newUser) {
        int res = 0;
        try (Connection connection = getMysqlConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS)) {
            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getEmail());
            statement.setInt(3, newUser.getId());
            res = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res > 0;
    }

    public User getUserById(int id) {
        User userOfBd = null;
        try (Connection connection = getMysqlConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                userOfBd = new User(id, name, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userOfBd;
    }
}
