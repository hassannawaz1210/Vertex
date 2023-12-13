package App.UI;

import App.BLL.EditAccount;
import App.BLL.User;
import App.Database.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class EditAccountController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField bioField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;
    
    private MainMenu mainMenu = MainMenu.getInstance();
    private EditAccount editAccountBLL =  new EditAccount();
    private User user = User.getInstance();
    private TitleBar titleBar;

    //=================== implementation ===================

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(user.getType().equals("Customer"))
        {
            bioField.setPromptText("bio");
        }
        else if(user.getType().equals("Publisher"))
        {
            bioField.setPromptText("company description");
        }
        
        titleBar = new TitleBar("Edit Account", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
    }

    @FXML
    void saveChanges(ActionEvent event) {
        if(!checkFields()) return;
        //whichever field is not empty, update it
        if(!bioField.getText().isEmpty())
        {
           editAccountBLL.updateBio(bioField.getText());
        }

        if(!emailField.getText().isEmpty())
        {
            user.setEmail(emailField.getText());
           editAccountBLL.updateEmail(emailField.getText());
        }

        if(!nameField.getText().isEmpty())
        {
            user.setName(nameField.getText());
            editAccountBLL.updateName(nameField.getText());
        }

        if(!passwordField.getText().isEmpty())
        {
            editAccountBLL.updatePassword(passwordField.getText());
        }

        if(!usernameField.getText().isEmpty())
        {
            user.setUsername(usernameField.getText());
            editAccountBLL.updateUsername(usernameField.getText());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Changes saved successfully.");
        alert.showAndWait();

    }

    private boolean checkFields()
    {
        if(bioField.getText().isEmpty() && emailField.getText().isEmpty() && nameField.getText().isEmpty() && passwordField.getText().isEmpty() && usernameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill at least one field.");
            alert.showAndWait();
            return false;
        }

        if(!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}") && !emailField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email.");
            alert.showAndWait();
            return false;
        }

        if(!usernameField.getText().matches("[a-zA-Z0-9]+") && !usernameField.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username must be letters and numbers only.");
            alert.showAndWait();
            return false;
        }

        return true;
    }

}
