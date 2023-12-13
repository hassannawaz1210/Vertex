package App.Database.DBHandler;

import java.sql.*;

import App.Database.Database;

public class CreditCardHandler  {

    private Database database;
    private Connection conn;

    public CreditCardHandler() {
        database = Database.getInstance();
        conn = database.getConnection();
    }

    public void addCard(String cardName, String cardNumber, String cardCVV, String cardMonth, String cardYear,
            int userID) {
        String query = "INSERT INTO card (name, cardNumber, cvv, year, month, userID) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            java.sql.PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cardName);
            stmt.setString(2, cardNumber);
            stmt.setString(3, cardCVV);
            stmt.setString(4, cardYear);
            stmt.setString(5, cardMonth);
            stmt.setInt(6, userID);
            stmt.executeUpdate();

            String query2 = "UPDATE user SET paymentAttached = 'true' WHERE userID = '" + userID + "'";
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate(query2);

            System.out.println("Payment added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deductCharges(int gameID,int userID)
    {
        String query_deduct = "UPDATE card SET balance = balance - (SELECT price FROM game WHERE gameID = '" + gameID + "') WHERE userID = '" + userID + "'";
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query_deduct);
            System.out.println("Charges deducted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
