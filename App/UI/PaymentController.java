package App.UI;

import java.sql.Connection;

import App.BLL.PaymentPageBLL;
import App.BLL.User;
import App.Database.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PaymentController {

    @FXML
    private TextField cardCVV;

    @FXML
    private TextField cardMonth;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardNumber;

    @FXML
    private TextField cardYear;

    private PaymentPageBLL paymentBLL = new PaymentPageBLL();

    public PaymentController() {
        paymentBLL = new PaymentPageBLL();
    }

    @FXML
    void onButtonClick(ActionEvent event) {
        String name = cardName.getText();
        String number = cardNumber.getText();
        String cvv = cardCVV.getText();
        String month = cardMonth.getText();
        String year = cardYear.getText();
        if (paymentBLL.addCard(name, number, cvv, month, year)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully added a card.");
            alert.showAndWait();
        }
    }
       

   
    
}
