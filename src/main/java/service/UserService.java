package service;

import model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(int id);
    void deleteUser(int id);
    void updateUser(User user);
    List<User> getAllUsers();
}
