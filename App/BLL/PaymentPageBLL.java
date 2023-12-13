package App.BLL;

import javafx.scene.control.Alert;

import App.Database.DBHandler.CreditCardHandler;
public class PaymentPageBLL {

    private CreditCardHandler ccHandler;

    public PaymentPageBLL() {
        ccHandler = new CreditCardHandler();
    }

    public boolean addCard(String name, String number, String cvv, String month, String year) {
        if(checkFields(name, number, cvv, month, year))
        {
            ccHandler.addCard(name, number, cvv, month, year, User.getInstance().getUserID());
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkFields( String cardName, String cardNumber, String cardCVV, String cardMonth, String cardYear) {
        if (cardName.isEmpty() || cardNumber.isEmpty() || cardCVV.isEmpty()
                || cardYear.isEmpty() || cardMonth.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields.");
            alert.showAndWait();
            return false;
        }
        if (cardNumber.length() != 16 || !cardNumber.matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid card number. Card number must be 16 digits.");
            alert.showAndWait();
            return false;
        }
        // name must be letters only
        if (!cardName.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Name must be letters only.");
            alert.showAndWait();
            return false;
        }
        // cvv must be 3 digits
        if (cardCVV.length() != 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("CVV must be 3 digits only.");
            alert.showAndWait();
            return false;
        }

        // year must be 4 digits
        if (cardYear.length() != 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Year must be 4 digits.");
            alert.showAndWait();
            return false;
        }

        if (!cardMonth.matches("[a-zA-Z]+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Month must be text only.");
            alert.showAndWait();
            return false;
        }

        else {
            return true;
        }

    }

}
