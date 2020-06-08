package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsersDAO();
    void addUserDAO(User user);
    void deleteUserDAO(int id);
    void updateUserDAO(User newUser);
    User getUserById(int id);
}
