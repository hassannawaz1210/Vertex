package App.Database.DBHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;

import App.Database.Database;

public class PublishGameHandler {

    Database database;
    Connection conn;

    public PublishGameHandler() {
        database = Database.getInstance();
        conn = database.getConnection();
    }

    public boolean addGame(String title , String description, String genre, String platform, String price, String releaseDate, String developer, String publisher, String filePath)
        {
        String query = "INSERT INTO game (title, description, genre, platform, price, releaseDate, developer, publisher, filePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, genre);
            stmt.setString(4, platform);
            stmt.setString(5, price);
            stmt.setString(6, releaseDate);
            stmt.setString(7, developer);
            stmt.setString(8, publisher);
            stmt.setString(9, filePath);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
       
    }
    
}
