package App.UI;

import java.io.IOException;

import App.BLL.SignUp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private MenuButton accTypeButton;

    @FXML
    private Button signupButton;

    @FXML
    private BorderPane borderPane;

    private SignUp signUpBLL;
    private TitleBar titleBar;
    // =================== implementation ===================

    public SignUpController() {
        signUpBLL = new SignUp();
    }

    @FXML
    void onButtonClick(ActionEvent event) {
        String email = emailField.getText();
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String accType = accTypeButton.getText();
        if (signUpBLL.signUp(email, password, name, username, accType)) {

            displayFields();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Account created successfully");
            alert.showAndWait();

        }
    }

    private void displayFields() {
        System.out.println("Email: " + emailField.getText());
        System.out.println("Name: " + nameField.getText());
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Password: " + passwordField.getText());
        System.out.println("Account Type: " + accTypeButton.getText());
    }

    @FXML
    void gotoSignInPage(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        LoginController controller = new LoginController();
        controller.setMainStage(stage);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void setAccType(ActionEvent event) {
        accTypeButton.setText(((MenuItem) event.getSource()).getText());
    }

 
}
