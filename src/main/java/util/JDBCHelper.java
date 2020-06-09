package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCHelper {
    public static final String URL = "jdbc:mysql://localhost:3306/user_table?useSSL=false&serverTimezone=UTC";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "ергпьфт";

    public static final String INSERT_USER = "insert into user (name, email) values (?, ?)";
    public static final String SELECT_USER_BY_ID = "select * from user where id = ?";
    public static final String SELECT_USER = "select * from user where name = ?, email = ?";
    public static final String SELECT_ALL_USERS = "select * from user";
    public static final String DELETE_USERS = "delete from user where id = ?";
    public static final String UPDATE_USERS = "update user set name = ?, email = ? where id = ?";

    public static Connection getMysqlConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
