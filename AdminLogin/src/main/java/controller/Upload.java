package controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Upload {
	String jdbcUrl = "jdbc:mysql://localhost:3306/eqdb";
	String username = "root";
	String password="admin";
	String sql = "insert into earthquake(Date,Time,Latitude,Longitude,Depth,Magnitude)values(?,?,?,?,?,?)";
	
	public int uploadFile(String firstName, String lastName, InputStream file) {
        int row = 0;
        

        try (Connection connection = DriverManager
            .getConnection(jdbcUrl, username, password);
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection
            .prepareStatement(sql)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            if (file != null) {
                // fetches input stream of the upload file for the blob column
                preparedStatement.setBlob(3, file);
            }

            // sends the statement to the database server
            row = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return row;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
