package App.Database.DBHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import App.Database.Database;

public class StoreHandler {

    private Database db;
    private Connection conn;

    public StoreHandler() {
        db = Database.getInstance();
        conn = db.getConnection();
    }

    
    public ArrayList<Map.Entry<String, String>> getGames()
    {
        String query = "SELECT title, filePath FROM game";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Map.Entry<String, String>> gamesList = new ArrayList<Map.Entry<String, String>>();
            while(rs.next())
            {
                String title = rs.getString("title");
                String imageName = rs.getString("filePath");
                gamesList.add(new java.util.AbstractMap.SimpleEntry<String, String>(title, imageName));
            }

            return gamesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
