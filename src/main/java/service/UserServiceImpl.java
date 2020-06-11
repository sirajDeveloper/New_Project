package service;

import dao.UserJdbcDAO;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    private UserJdbcDAO userDao = new UserJdbcDAO();

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    private UserServiceImpl() {}

    @Override
    public void addUser(User user) {
        if (user.getId() == null) {
            userDao.addUserDAO(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUserDAO(id);
    }

    @Override
    public void updateUser(User user) {
        User userOfBd = userDao.getUserById(user.getId());
        if (userOfBd != null) {
            userDao.updateUserDAO(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsersDAO();
    }
}