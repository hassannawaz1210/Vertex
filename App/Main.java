package App;

import java.net.URL;

import App.UI.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UI/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            LoginController loginController = loader.getController();
            loginController.setMainStage(primaryStage);
           
            //--------------------
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Vertex");
            // set icon
            URL url = getClass().getResource("UI/assets/logo.png");
            Image logo = new Image(url.toExternalForm());
            primaryStage.getIcons().add(logo);
            // no borders
            primaryStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}