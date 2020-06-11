package factory;

import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

public class UserHiberDaoFactory implements DaoFactory {

    @Override
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
