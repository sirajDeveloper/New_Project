package service;

import dao.UserDAOImpl;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl userService;
    private UserDAOImpl userDao = new UserDAOImpl();

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public void addUser(User user) {
        User userOfBd = userDao.getUserById(user.getId());
        if (userOfBd == null) {
            userDao.addUserDAO(user);
        }
    }

    @Override
    public User getUserById(int id) {
        return new UserDAOImpl().getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        new UserDAOImpl().deleteUserDAO(id);
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
        return new UserDAOImpl().getAllUsersDAO();
    }
}
