package App.Database.DBHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import App.Database.Database;

public class HomeHandler {

    private Database db;
    private Connection conn;
    private ArrayList<Map.Entry<String, String>> gamesList;

    public HomeHandler() {
        db = Database.getInstance();
        conn = db.getConnection();
        gamesList = new ArrayList<Map.Entry<String, String>>();
    }

    public ArrayList<Map.Entry<String, String>> getGames()
    {
        String query = "SELECT * FROM game ORDER BY gameID ASC";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                String title = rs.getString("title");
                String imageName = rs.getString("filePath");
                gamesList.add(new SimpleEntry<String, String>(title, imageName));
            }
            return gamesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
