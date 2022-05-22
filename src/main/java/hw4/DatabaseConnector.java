package hw4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final DatabaseConnector DATABASE_CONNECTOR = new DatabaseConnector();
    private Connection connection;
    private DatabaseConnector(){
        try{
            String dbUrl = "jdbc:mysql://127.0.0.1:3306/home_work_3";
            String dbUser = "root";
            String dbPass = "1234";
            DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector();
            connection = DriverManager.getConnection(dbUrl,dbUser,dbPass);
        }

        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static DatabaseConnector getDatabaseConnector(){
        return DATABASE_CONNECTOR;
    }
    public Connection getConnection(){
        return connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
    }


}
