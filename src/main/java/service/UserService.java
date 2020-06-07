package service;

import dao.UserDAO;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAO userDao;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public boolean addUser(User user) {
        if (new UserDAO().addUserDAO(user)) {
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return new UserDAO().getUserById(id);
    }

    public boolean deleteUser(int id) {
        return new UserDAO().deleteUserDAO(id);
    }

    public boolean updateUser(User user) {
        userDao = new UserDAO();
        User userOfBd = userDao.getUserById(user.getId());
        if (userOfBd != null) {
            userDao.updateUserDAO(user);
        }
        return false;
    }

    public List<User> getAllUsers() {
        return new UserDAO().getAllUsersDAO();
    }
}
