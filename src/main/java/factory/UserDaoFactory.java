package factory;

import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory implements DaoFactory {

    public UserDAO createDAO() {
        Properties property = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
