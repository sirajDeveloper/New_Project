package factory;

import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserJdbcDaoFactory implements DaoFactory {

    public UserDAO createDAO() {
        String daoType = ApplicationConfiguration.getPropertyDAO();
        String userDaoClassName = UserJdbcDAO.class.getSimpleName();
        if (userDaoClassName.equals(daoType)) {
           return new UserJdbcDAO();
        }
        return null;
    }
}
