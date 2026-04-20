package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBContext {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/quanlytrungtam?useSSL=false&serverTimezone=UTC";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "123456";
    private static final String CONFIG_FILE = "db.properties";

    private static final Properties CONFIG = loadConfig();
    private static final String URL = getConfig("DB_URL", "db.url", "db.url", DEFAULT_URL);
    private static final String USER = getConfig("DB_USER", "db.user", "db.user", DEFAULT_USER);
    private static final String PASSWORD = getConfig("DB_PASSWORD", "db.password", "db.password", DEFAULT_PASSWORD);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Khong the nap MySQL JDBC Driver", e);
        }
    }

    private DBContext() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static Properties loadConfig() {
        Properties properties = new Properties();

        try (InputStream inputStream = DBContext.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Khong the doc file cau hinh db.properties", e);
        }

        return properties;
    }

    private static String getConfig(String envKey, String systemPropertyKey, String fileKey, String defaultValue) {
        String value = System.getenv(envKey);

        if (value == null || value.trim().isEmpty()) {
            value = System.getProperty(systemPropertyKey);
        }

        if (value == null || value.trim().isEmpty()) {
            value = CONFIG.getProperty(fileKey);
        }

        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        return value.trim();
    }
}