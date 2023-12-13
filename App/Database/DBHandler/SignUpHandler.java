package App.Database.DBHandler;

import java.sql.*;

import App.Database.Database;

public class SignUpHandler{

    private Database db;
    private Connection conn;


    public SignUpHandler() {
        db = Database.getInstance();
        conn = db.getConnection();
    }

    public boolean exists(String email , String username) {
        String query = "SELECT * FROM user WHERE email = '" + email + "' OR username = '" + username + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUser( String email, String password, String name, String username, String accType){
        try {

            // Insert into user table
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (name, email, password, username, type) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, username);
                stmt.setString(5, accType);
                stmt.executeUpdate();

                // Get the customerID of the newly inserted customer
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                int userID = rs.getInt(1);

            if (accType.equals("Customer")) {
                // Insert into customer table
                PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO customer (userID, accountBalance) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                stmt1.setInt(1, userID);
                stmt1.setFloat(2, 0.0f);
                stmt1.executeUpdate();
                System.out.println("Customer has been succsessfully added to the database.");

            } else if (accType.equals("Publisher")) {
               PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO publisher (userID) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS);
                stmt1.setInt(1, userID);
                stmt1.executeUpdate();
                System.out.println("Publisher has been succsessfully added to the database.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}