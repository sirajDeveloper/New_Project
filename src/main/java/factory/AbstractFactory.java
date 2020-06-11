package factory;

public class AbstractFactory {
    DaoFactory createFactory(String factoryType) {
        switch (factoryType) {
            case "UserDao":
                return new UserDaoFactory();
            default:
                return null;
        }
    }
}
