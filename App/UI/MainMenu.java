package App.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import App.BLL.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class MainMenu {

    private static MainMenu instance;

    private Stage mainStage;

    private AnchorPane background1;
    private Text title;
    private Line separatorLine;
    private VBox menu;
    private ArrayList<Button> menuButtons;
    private Button currentButton;
    private Button logout;
    String[] customerButtons = new String[] { "HOME", "LIBRARY", "STORE", "ATTACH CARD", "EDIT ACCOUNT" };
    String[] publusherButtons = new String[] { "HOME", "STORE", "EDIT ACCOUNT", "PUBLISH GAME" };

    User user = User.getInstance();

    private MainMenu() {
        init();
    }

    private void init() {
        background1 = new AnchorPane();
        background1.setPrefHeight(662);
        background1.setPrefWidth(232);
        background1.setStyle("-fx-background-color: #222222;");

        title = new Text("Vertex");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrappingWidth(198.02);
        title.setFont(new Font(34.0));
        title.setFill(Color.WHITE);
        AnchorPane.setTopAnchor(title, 37.31);
        AnchorPane.setLeftAnchor(title, 20.0);

        separatorLine = new Line();
        separatorLine.setStartX(10.0);
        separatorLine.setStartY(130.0);
        separatorLine.setEndX(232.0);
        separatorLine.setEndY(130.0);
        separatorLine.setFill(Color.WHITE);
        separatorLine.setStroke(Color.WHITE);

        menu = new VBox();
        menu.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setTopAnchor(menu, 150.0);
        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setRightAnchor(menu, 0.0);
        AnchorPane.setBottomAnchor(menu, 10.0);
        menu.setSpacing(10.0);

        menuButtons = new ArrayList<Button>();

        if (user.getType().equals("Customer")) {

            for (String child : customerButtons) {
                Button menuButton = new Button(child);
                menuButton.setPrefWidth(200);
                menuButton.setPrefHeight(25.0);
                menuButton.getStyleClass().add("menuButton");
                menuButton.setOnMouseEntered(e -> {
                    menuButton.setStyle("-fx-background-color: #035C4D;");
                });
                menuButton.setOnMouseExited(e -> {
                    if (menuButton != currentButton)
                        menuButton.setStyle("-fx-background-color: #222222;");
                });
                menuButtons.add(menuButton);
            }

            // Home Botton
            menuButtons.get(0).onActionProperty().setValue(this::gotoHomePage);

            // Library Button
            menuButtons.get(1).onActionProperty().setValue(this::gotoLibraryPage);

            // Store Button
            menuButtons.get(2).onActionProperty().setValue(this::gotoStorePage);

            // Attach Card Button
            menuButtons.get(3).onActionProperty().setValue(this::openPaymentPage);

            // Edit Account Button
            menuButtons.get(4).onActionProperty().setValue(this::gotoEditAccountPage);

        } else if (user.getType().equals("Publisher")) {
            for (String child : publusherButtons) {
                Button menuButton = new Button(child);
                menuButton.setPrefWidth(200);
                menuButton.setPrefHeight(25.0);
                menuButtons.add(menuButton);
                menuButton.getStyleClass().add("menuButton");
            }

            // Home Botton
            menuButtons.get(0).onActionProperty().setValue(this::gotoHomePage);

            // Store Button
            menuButtons.get(1).onActionProperty().setValue(this::gotoStorePage);

            // Edit Account Button
            menuButtons.get(2).onActionProperty().setValue(this::gotoEditAccountPage);

            // Publish Game Button
            menuButtons.get(3).onActionProperty().setValue(this::gotoPublishPage);

        }

        logout = new Button("LOGOUT");
        logout.setPrefWidth(200);
        logout.setPrefHeight(25.0);
        logout.getStyleClass().add("menuButton");
        logout.setOnAction(this::logout);
        logout.setStyle("-fx-background-color: #ff0000ce;");
        AnchorPane.setBottomAnchor(logout, 20.0);
        AnchorPane.setLeftAnchor(logout, 10.0);
        AnchorPane.setRightAnchor(logout, 0.0);

        menu.getChildren().addAll(menuButtons);
        background1.getChildren().addAll(title, separatorLine, menu, logout);

        currentButton = menuButtons.get(0);
        currentButton.setStyle("-fx-background-color: #035C4D;");
    }

    public void gotoHomePage(ActionEvent event) {
        if (currentButton.getText().equals("HOME"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            currentButton = menuButtons.get(0);
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoLibraryPage(ActionEvent event) {
        if (currentButton.getText().equals("LIBRARY"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            currentButton = menuButtons.get(1);
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("library.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoStorePage(ActionEvent event) {
        if (currentButton.getText().equals("STORE"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            for (Button button : menuButtons) {
                if (button.getText().equals("STORE")) {
                    currentButton = button;
                    break;
                }
            }
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("store.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPaymentPage(ActionEvent event) {
        if (currentButton.getText().equals("ATTACH CARD"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            currentButton = menuButtons.get(3);
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("payment.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setTitle("Vertex");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoEditAccountPage(ActionEvent event) {
        if (currentButton.getText().equals("EDIT ACCOUNT"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            for (Button button : menuButtons) {
                if (button.getText().equals("EDIT ACCOUNT")) {
                    currentButton = button;
                    break;
                }
            }
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editAccount.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.centerOnScreen();
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void gotoPublishPage(ActionEvent event) {
        if (currentButton.getText().equals("PUBLISH GAME"))
            return;
        try {
            currentButton.setStyle("-fx-background-color: #222222;");
            for (Button button : menuButtons) {
                if (button.getText().equals("PUBLISH GAME")) {
                    currentButton = button;
                    break;
                }
            }
            currentButton.setStyle("-fx-background-color: #035C4D;");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("publishGame.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.centerOnScreen();
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void logout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.centerOnScreen();
            mainStage.show();
            deleteInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public AnchorPane getRoot() {
        return background1;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setCurrentButton() {
        currentButton.setStyle("-fx-background-color: #222222;");
        this.currentButton = new Button();
    }

    private void deleteInstance() {
        instance = null;
        user = null;
    }

    public Stage getMainStage() {
        return mainStage;
    }
}