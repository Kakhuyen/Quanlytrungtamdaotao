package util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DBContext.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException("Khong the ket noi CSDL", ex);
        }
    }
}