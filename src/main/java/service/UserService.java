package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService implements UserServiceImpl{
    private static UserService userService;
    private UserDAO userDao;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public void addUser(User user) {
        userDao = new UserDAO();
        User userOfBd = userDao.getUserById(user.getId());
        if (userOfBd == null) {
            new UserDAO().addUserDAO(user);
        }
    }

    @Override
    public User getUserById(int id) {
        return new UserDAO().getUserById(id);
    }

    @Override
    public void deleteUser(int id) {
        new UserDAO().deleteUserDAO(id);
    }

    @Override
    public void updateUser(User user) {
        userDao = new UserDAO();
        User userOfBd = userDao.getUserById(user.getId());
        if (userOfBd != null) {
            userDao.updateUserDAO(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new UserDAO().getAllUsersDAO();
    }
}
