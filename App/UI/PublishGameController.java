package App.UI;

import java.net.URL;
import java.util.ResourceBundle;

import App.BLL.PublishGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PublishGameController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField developerField;

    @FXML
    private TextField filenameField;

    @FXML
    private TextArea gameDescField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField platformField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField titleField;

    private MainMenu mainMenu = MainMenu.getInstance();
    private PublishGame publishGameBLL = new PublishGame();
    private TitleBar titleBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      titleBar = new TitleBar("Publish game", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
    }

    @FXML
    void onPublish(ActionEvent event) {
      String title = titleField.getText();
      String description = gameDescField.getText();
      String genre = genreField.getText();
      String platform = platformField.getText();
      String price = priceField.getText();
      String releaseDate = "";
      if(dateField.getValue() != null)
      releaseDate = dateField.getValue().toString();
      String developer = developerField.getText();
      String publisher = publisherField.getText();
      String filePath = filenameField.getText();
      
        if(publishGameBLL.publish(title, description, genre, platform, price, releaseDate, developer, publisher, filePath))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully published a game.");
            alert.showAndWait();
        }

    }

   

}
