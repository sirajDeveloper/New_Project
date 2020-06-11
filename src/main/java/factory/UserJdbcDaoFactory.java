package factory;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_INPeer;
import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class UserJdbcDaoFactory implements DaoFactory {

    public UserDAO createDAO() {
        String daoType = ApplicationConfiguration.getPropertyDAO();

        switch (daoType) {
            case "UserHiberDAO":
                return new UserHiberDAO();
            default:
                return new UserJdbcDAO();
        }
    }
}
