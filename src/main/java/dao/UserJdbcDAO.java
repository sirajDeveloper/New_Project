package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO() {
        connection = DBHelper.getDBHelper().getMysqlConnection();
    }

    @Override
    public List<User> getAllUsersDAO() {
        String query = "select * from user";
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public void addUserDAO(User user) {
        String query = "insert into user (name, email, password, role) values (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteUserDAO(Long id) {
        String query = "delete from user where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateUserDAO(User newUser) {
        String query = "update user set name = ?, email = ?, password = ?, role = ? where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPassword());
            statement.setString(4, newUser.getRole());
            statement.setLong(5, newUser.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id) {
        String query = "select * from user where id = ?";
        User userOfBd = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                userOfBd = new User(id, name, email, password, role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userOfBd;
    }

    @Override
    public User getRoleUserDAO(String userEmail, String userPassword) {
        String query = "select * from user where email = ? and password = ?";
        User userOfBd = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEmail);
            statement.setString(2, userPassword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                userOfBd = new User(id, name, email, password, role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userOfBd;
    }
}
