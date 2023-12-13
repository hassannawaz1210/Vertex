package App.Database.DBHandler; 

import java.sql.*;

import App.Database.Database;
import javafx.scene.chart.PieChart.Data;

public class ReportHandler {

    private Database database;
    private Connection conn;

    public ReportHandler() {
        database = Database.getInstance();
        conn = database.getConnection();
    }

    public void addReport(String reason, String attachmentPath, int gameID, int userID) {
        String query = "INSERT INTO report (reason, attachment, gameID, userID ) VALUES (?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, reason);
            stmt.setString(2, attachmentPath);
            stmt.setInt(3, gameID);
            stmt.setInt(4, userID);
            stmt.executeUpdate();
            System.out.println("Report added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getGameTitle(int gameID) {
        String query = "SELECT title FROM game WHERE gameID = '" + gameID + "'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String title = rs.getString("title");
                return title;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
