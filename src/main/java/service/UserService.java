package service;

import model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    List<User> getAllUsers();
}
