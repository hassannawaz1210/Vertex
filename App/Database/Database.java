package App.Database;

import java.sql.*;

public class Database {

    private static Database instance;

    static final String DB_URL = "jdbc:mysql://localhost:3306/vertex";
    static final String USER = "root";
    static final String PASS = "1234lol";
    static final String QUERY = "SELECT * FROM customer";

    private Connection conn;

    private Database() {
       init();
    }

    private void init() {
        //Opening a connection
        try {
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Connected to database");

        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if(instance == null)
        {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
