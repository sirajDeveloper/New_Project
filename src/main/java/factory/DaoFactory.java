package factory;

import dao.UserDAO;

public interface DaoFactory {
    UserDAO createDAO();
}
