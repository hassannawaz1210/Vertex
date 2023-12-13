package App.UI;

import App.BLL.Library;
import App.BLL.User;
import App.Database.*;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LibraryController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @FXML
    VBox bodyPage;

    @FXML
    private Text nameOfUser;

    @FXML
    private Text typeOfUser;

    private MainMenu mainMenu = MainMenu.getInstance();
    private ArrayList<Map.Entry<String, String>> gamesList;
    private User user = User.getInstance();
    private Library libraryBLL = new Library();
    private TitleBar titleBar;

     //=================== implementation ===================

     

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        titleBar = new TitleBar("Lirary", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
        gamesList = libraryBLL.getGames();
        displayGames();
        updateNavBar();
    }

    private void updateNavBar()
    {
        nameOfUser.setText(user.getName().toUpperCase());
        typeOfUser.setText(user.getType());
    }

    private void displayGames() {
        //------------- first we fetch games from database 
        //------------- Then we run a loop number of childs that are in gameHbox1
        //------------- For each child, we set the title and image
        try {
            HBox currentRow = addRow();
            int maxCards = 4;
            for(Map.Entry<String, String> game : gamesList)
            {
                String gameTitle = game.getKey();
                String gameImage = game.getValue();
                if(maxCards == 0)
                {
                    currentRow = addRow();
                    maxCards = 4;
                }
                VBox card = createCard();
                ImageView imageView = (ImageView) card.getChildren().get(0);
                Text text = (Text) card.getChildren().get(1);
                text.setText(gameTitle);
                URL url = getClass().getResource("assets/" + gameImage);
                Image image = new Image(url.toExternalForm());
                imageView.setImage(image);
                currentRow.getChildren().add(card);
                maxCards--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
      
    }

    private HBox addRow()
    {
        HBox row = new HBox();
        row.setSpacing(15);
        row.setAlignment(Pos.TOP_CENTER);
        row.setPrefHeight(Region.USE_COMPUTED_SIZE);
        row.setPrefWidth(Region.USE_COMPUTED_SIZE);  
        bodyPage.getChildren().add(row); 
        return row;
    }
    private VBox createCard()
    {
        VBox vb = new VBox();
        vb.setSpacing(5);
        vb.setPrefHeight(Region.USE_COMPUTED_SIZE);
        vb.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vb.getStyleClass().add("card");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(160);
        imageView.setFitWidth(130);
        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(128.68518447);
        vb.getChildren().add(imageView);
        vb.getChildren().add(text);
        return vb;
    }
    
}
