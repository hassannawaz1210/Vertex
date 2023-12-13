package App.UI;

import App.BLL.Login;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private BorderPane borderPane;

    private Stage mainStage;

    private Login loginBLL;
    private TitleBar titleBar;

    // =================== implementation ===================

    public LoginController() {
        loginBLL = new Login();
    }

    @FXML
    void gotoSignUpPage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gotoHomePage(Stage stage) {
        try {
            MainMenu mainMenu = MainMenu.getInstance();
            mainMenu.setMainStage(stage);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onButtonClick(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        String email = emailField.getText();
        String password = passwordField.getText();
        if (loginBLL.login(email, password)) {
            System.out.println("Logged in successfully.");
            displayFields();
            gotoHomePage(stage);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Login failed. Please try again.");
            alert.showAndWait();
            System.out.println("Login failed");
        }
    }

    private void displayFields() {
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + passwordField.getText());
    }

    public void setMainStage(Stage stage) {
        mainStage = stage;
    }

}
