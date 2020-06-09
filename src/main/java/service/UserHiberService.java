package service;

import dao.UserDAO;
import dao.UserHiberDAO;
import model.User;

import java.util.List;

public class UserHiberService implements UserService {

    private static UserHiberService userHiberService;
    private UserHiberDAO userHiberDAO = new UserHiberDAO();

    public static UserHiberService getUserHiberService() {
        if (userHiberService == null) {
            userHiberService = new UserHiberService();
        }
        return userHiberService;
    }

    private UserHiberService() {}

    @Override
    public List<User> getAllUsers() {
        return userHiberDAO.getAllUsersDAO();
    }

    @Override
    public void addUser(User user) {
        userHiberDAO.addUserDAO(user);
    }

    @Override
    public void deleteUser(int id) {
        userHiberDAO.deleteUserDAO(id);
    }

    @Override
    public void updateUser(User newUser) {
        userHiberDAO.updateUserDAO(newUser);
    }

    @Override
    public User getUserById(int id) {
        return userHiberDAO.getUserById(id);
    }
}
