package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public static Connection conn;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quanlytrungtam?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8",
                    "root",
                    "root"
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Khong ket noi duoc MySQL");
        }
    }
}