package App.UI;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import App.BLL.Store;
import App.BLL.User;
import App.Database.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StoreController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField searchField;

    @FXML
    private VBox bodyPage;

    @FXML
    private Text nameOfUser;

    @FXML
    private Text typeOfUser;

    private Database db = Database.getInstance();
    private User user = User.getInstance();
    private MainMenu mainMenu = MainMenu.getInstance();
    private Store storeBLL = new Store();
    ArrayList<Map.Entry<String, String>> gamesList;
    private TitleBar titleBar;

    //=================== implementation ===================

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        titleBar = new TitleBar("Store", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
        gamesList = storeBLL.getGames();
        displayGames();
        updateNavBar();
    }

    private void updateNavBar()
    {
        nameOfUser.setText(user.getName().toUpperCase());
        typeOfUser.setText(user.getType());
    }

        @FXML
    void searchGame(ActionEvent event) {
        Connection conn = db.getConnection();
        String query = "SELECT title, filePath FROM game WHERE title LIKE '%" + searchField.getText() + "%'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            bodyPage.getChildren().clear();
            HBox currentRow = addRow();
            int maxCards = 4;
            while(rs.next())
            {
                if(maxCards == 0)
                {
                    currentRow = addRow();
                    maxCards = 4;
                }
                VBox card = createCard();
                String title = rs.getString("title");
                String imageName = rs.getString("filePath");

                for (Node child : card.getChildren()) {
                        if (child instanceof Text) {
                            Text text = (Text) child;
                            text.setText(title);
                        } else if (child instanceof ImageView) {
                            ImageView imageView = (ImageView) child;
                            URL url = getClass().getResource("assets/" + imageName);
                            Image image = new Image(url.toExternalForm());
                            imageView.setImage(image);
                        }
                    }
                    
                currentRow.getChildren().add(card);

                maxCards--;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayGames(){  
        try {
            int maxCards = 4;
            HBox currentRow = addRow();
            for(Map.Entry<String, String> game : gamesList)
            {
                String title = game.getKey();
                String imageName = game.getValue();

                if(maxCards == 0)
                {
                    currentRow = addRow();
                    maxCards = 4;
                }
                VBox card = createCard();
                for (Node child : card.getChildren()) {
                        if (child instanceof Text) {
                            Text text = (Text) child;
                            text.setText(title);
                        } else if (child instanceof ImageView) {
                            ImageView imageView = (ImageView) child;
                            URL url = getClass().getResource("assets/" + imageName);
                            Image image = new Image(url.toExternalForm());
                            imageView.setImage(image);
                        }
                    }
                    
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
        vb.setStyle("-fx-cursor: hand;");
        vb.setPrefHeight(Region.USE_COMPUTED_SIZE);
        vb.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vb.getStyleClass().add("card");
        vb.onMouseClickedProperty().setValue(this::gotoGamePage);
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

         public void gotoGamePage(MouseEvent event)
    {
        //------------- When a game is clicked, we first find out which game was it
        //------------- then i pass on that title to gamepage in order to get the rest of game data with the 
        //------------- help of that title

        Node node = (Node) event.getSource();
        mainMenu.setCurrentButton();
        String title = "";
        if(node instanceof VBox)
        {
            VBox vbox = (VBox) node;
            for(Node child : vbox.getChildren())
            {
                if(child instanceof Text)
                {
                    Text text = (Text) child;
                    title = text.getText();
                    System.out.println("GAME: " + title);
                }
            }
        }
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("gamePage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            GameController controller = loader.getController();
            controller.setTitle(title);
            controller.setPrevScene("store");
            controller.run();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();          
        }
    }
    
}
