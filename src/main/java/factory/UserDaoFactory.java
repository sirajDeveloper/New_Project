package factory;

import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory implements DaoFactory {

    public UserDAO createDAO() {
        String daoType = ApplicationConfiguration.getPropertyDAO();
        UserDAO userDAO = null;
        switch (daoType) {
            case "UserJdbcDAO":
                userDAO = new UserJdbcDAO();
                break;
            case "UserHiberDAO":
                userDAO = new UserHiberDAO();
                break;
        }
        return userDAO;
    }
}
