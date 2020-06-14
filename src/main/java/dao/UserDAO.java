package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsersDAO();
    void addUserDAO(User user);
    void deleteUserDAO(Long id);
    void updateUserDAO(User newUser);
    User getUserById(Long id);
    User getRoleUserDAO(String email, String password);
}
