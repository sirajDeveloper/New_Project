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
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBHelper.SELECT_ALL_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public void addUserDAO(User user) {
        try (PreparedStatement statement = connection.prepareStatement(DBHelper.INSERT_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteUserDAO(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DBHelper.DELETE_USERS)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateUserDAO(User newUser) {
        try (PreparedStatement statement = connection.prepareStatement(DBHelper.UPDATE_USERS)) {
            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getEmail());
            statement.setLong(3, newUser.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id) {
        User userOfBd = null;
        try (PreparedStatement statement = connection.prepareStatement(DBHelper.SELECT_USER_BY_ID)) {
            statement.setLong(1, id);
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
