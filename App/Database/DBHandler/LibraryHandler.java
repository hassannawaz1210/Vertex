package App.Database.DBHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import App.BLL.User;
import App.Database.Database;

public class LibraryHandler {

    private Database database;
    private Connection conn;
    private User user;
    ArrayList<Map.Entry<String, String>> gamesList;

    public LibraryHandler() {
        database = Database.getInstance();
        conn = database.getConnection();
        user = User.getInstance();
        gamesList = new ArrayList<Map.Entry<String, String>>();
    }

    public ArrayList<Map.Entry<String, String>> getGames() {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT game.title, game.filePath FROM user INNER JOIN owns on user.userID = owns.userID INNER JOIN game on owns.gameID = game.gameID WHERE user.userID = '"
                    + user.getUserID() + "'";
            ResultSet rs = stmt.executeQuery(query);
             
            while (rs.next()) {
                String title = rs.getString("title");
                String imageName = rs.getString("filePath");
                gamesList.add(new java.util.AbstractMap.SimpleEntry<String, String>(title, imageName));
            }
            return gamesList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
