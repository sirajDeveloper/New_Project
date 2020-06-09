package dao;

import java.util.Properties;

public class UserDaoFactory {
    public UserDAO getDAO(Properties property) {
        String dao = property.getProperty("daotype");
        UserDAO userDAO = null;
        switch (dao) {
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
