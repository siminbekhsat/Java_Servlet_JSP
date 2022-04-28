package com.siminproject.assignment2_simin_servlet_jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is representative of connections to the database.
 * Using the given URL, username and password, provides the connection to database requirements
 */
public class DBConnection {
    static private final String DB_URL = "jdbc:mariadb://localhost:3306/books";
    static private final String USER_NAME = "root";
    private static final String PASSWORD = "4165";

    /**
     * Returns a connection object based on the given URL, Username and Password
     * @return Connection (of type Connection)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection initDatabase() throws SQLException, ClassNotFoundException {
        try {
            Connection connection = DriverManager.getConnection(
                    DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connection is OK now -- - - - - --");
            return connection;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException("Cannot connect to database: ", sqlException);
        }
    }
}
