package dao;

import java.sql.Connection;
import java.sql.SQLException;
import util.DBContext;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }
}