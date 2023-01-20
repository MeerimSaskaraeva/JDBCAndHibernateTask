package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/java",
                    "postgres",
                    "2202"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // реализуйте настройку соеденения с БД
}
