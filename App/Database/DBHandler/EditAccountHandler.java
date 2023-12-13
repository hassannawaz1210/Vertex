package App.Database.DBHandler;

import java.sql.*;

import javax.swing.plaf.nimbus.State;

import App.BLL.User;
import App.Database.Database;

public class EditAccountHandler {

    Database database;
    Connection conn;
    User user;

    public EditAccountHandler() {
        database = Database.getInstance();
        conn = database.getConnection();
        user = User.getInstance();
    }

    public void updateBio(String bio) {
        String query = "UPDATE user SET bio = '" + bio + "' WHERE userID = '" + user.getUserID() + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(String email)
    {
        String query = "UPDATE user SET email = '" + email + "' WHERE userID = '" + user.getUserID() + "'";
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String password) {
        String query = "UPDATE user SET password = '" + password + "' WHERE userID = '" + user.getUserID() + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateUsername(String username) {
        String query = "UPDATE user SET username = '" + username + "' WHERE userID = '" + user.getUserID() + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateName(String name) {
        String query = "UPDATE user SET name = '" + name + "' WHERE userID = '" + user.getUserID() + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
