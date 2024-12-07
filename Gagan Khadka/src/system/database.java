package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            String url ="jdbc:mysql://127.0.0.1:3306/course management system";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle the exception properly in a real application
        }
        return connection;

    }
}
