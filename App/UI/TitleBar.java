package App.UI;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TitleBar {
    @FXML
    private AnchorPane bg;

    @FXML
    private Text title;

    private Stage primaryStage;

    public TitleBar(String title, Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("titlebar.fxml"));
            loader.setController(this);
            loader.load();
            setTitle(title);

            // move around thread
            Platform.runLater(() -> {
                // Add mouse pressed and dragged listeners to the scene
                double[] offset = new double[] { 0, 0 };
                bg.setOnMousePressed(event -> {
                    offset[0] = event.getSceneX();
                    offset[1] = event.getSceneY();
                });
                bg.setOnMouseDragged(event -> {
                    primaryStage.setX(event.getScreenX() - offset[0]);
                    primaryStage.setY(event.getScreenY() - offset[1]);
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public Node getRoot() {
        return bg;
    }

    @FXML
    void close(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                System.exit(0);
            } else if (type == buttonTypeNo) {
                alert.close();
            }
        });
    }

    @FXML
    void minimize(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Minimize the window
        stage.setIconified(true);
    }

}
