package App.Database.DBHandler;

import java.sql.*;

import App.BLL.User;
import App.Database.Database;

public class GameHandler {

    private Database db;
    private Connection conn;
    private User user;

    public GameHandler() {
        db = Database.getInstance();
        conn = db.getConnection();
        user = User.getInstance();
    }

    public boolean isCardAttached() {
        String query = "SELECT paymentAttached FROM user WHERE userID = '" + user.getUserID() + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String paymentAttached = rs.getString("paymentAttached");
                if (paymentAttached.equals("true")) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAlreadyBought(int gameID) {
        String query = "SELECT * FROM owns WHERE userID = '" + user.getUserID() + "' AND gameID = '" + gameID + "'";
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void addToLibrary(int gameID) {
        String query = "INSERT INTO owns (userID, gameID) VALUES ('" + user.getUserID() + "', '" + gameID + "')";
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Game added to library");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   

}
