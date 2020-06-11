package factory;

import dao.UserDAO;
import dao.UserHiberDAO;
import dao.UserJdbcDAO;

public class UserHiberDaoFactory implements DaoFactory {

    @Override
    public UserDAO createDAO() {
        String daoType = ApplicationConfiguration.getPropertyDAO();
        String userDaoClassName = UserHiberDAO.class.getSimpleName();
        if (userDaoClassName.equals(daoType)) {
            return new UserHiberDAO();
        }
        return null;
    }
}
