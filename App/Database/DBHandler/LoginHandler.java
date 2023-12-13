package App.Database.DBHandler;

import java.sql.*;
import java.util.ArrayList;

import App.BLL.Login;
import App.BLL.User;
import App.Database.Database;

public class LoginHandler {

    private Database db; 
    private Connection conn;

    public LoginHandler() {
        db = Database.getInstance();
        conn = db.getConnection();
    }

    public boolean exists(String email , String password) {
        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
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

    public ArrayList<String> getUserData(String email, String password)
    {
        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        ArrayList<String> userData = new ArrayList<String>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                userData.add(rs.getString("userID"));
                userData.add(rs.getString("email"));
                userData.add(rs.getString("name"));
                userData.add(rs.getString("username"));
                userData.add(rs.getString("type"));
                return userData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
