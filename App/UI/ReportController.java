package App.UI;

import java.io.File;

import App.BLL.Report;
import App.BLL.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReportController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private TextArea reasonField;

    @FXML
    private Button uploadButton;

    @FXML
    private Text reportGameTitle;

    private String attachmentPath;
    private String previousScene;
    private MainMenu mainMenu = MainMenu.getInstance();
    private User user = User.getInstance();
    private Report reportBLL = new Report();
    private TitleBar titleBar;

    int gameID;

    // =================== implementation ===================

    public void run() {
        titleBar = new TitleBar("Report", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
        updatePage();
    }

    @FXML
    public void goBack(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gamePage.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            GameController controller = loader.getController();
            controller.setTitle(reportGameTitle.getText());
            controller.setPrevScene(previousScene);
            controller.run();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onButtonClick(ActionEvent event) {
        String reason = reasonField.getText();
        String attachment = attachmentPath;
        int userID = user.getUserID();

        reportBLL.submitReport(reason, attachment, gameID, userID);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Report submitted successfully");
        alert.showAndWait();
        goBack(event);
    }

    private void updatePage() {
        String title = reportBLL.getGameTitle(gameID);
        reportGameTitle.setText(title);
    }

    @FXML
    private void uploadButtonOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            uploadButton.setText(file.getName());
            attachmentPath = file.getAbsolutePath();
        }
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setPrevScene(String scene) {
        this.previousScene = scene;
    }
}
