
package App.UI;

import App.BLL.User;
import App.BLL.Home;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    @FXML
    private HBox gameHbox1;

    @FXML
    private HBox gameHbox2;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button homeMenuButton;

    @FXML
    private Text nameOfUser;

    @FXML
    private Text typeOfUser;

    private ArrayList<Map.Entry<String, String>> gamesList;

    private User user = User.getInstance();
    private MainMenu mainMenu = MainMenu.getInstance();
    private Home homeBLL = new Home();
    private TitleBar titleBar;

    // =================== implementation ===================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleBar = new TitleBar("Home", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
        gamesList = homeBLL.getGames();
        updateNavBar();
        displayGames();
    }

    private void updateNavBar() {
        nameOfUser.setText(user.getName().toUpperCase());
        typeOfUser.setText(user.getType());
    }

    private void displayGames() {
        int iterator = 0;
        for (Node child : gameHbox1.getChildren()) {
            if (child instanceof VBox) {
                VBox vbox = (VBox) child;
                for (Node grandChild : vbox.getChildren()) {
                    if (grandChild instanceof Text) {
                        String title = gamesList.get(iterator).getKey();
                        Text text = (Text) grandChild;
                        text.setText(gamesList.get(iterator).getKey());
                    } else if (grandChild instanceof ImageView) {
                        String imageName = gamesList.get(iterator).getValue();
                        URL url = getClass().getResource("assets/" + imageName);
                        ImageView imageView = (ImageView) grandChild;
                        imageView.setImage(new Image(url.toString()));
                    }
                }
            }
            iterator++;
        }

        iterator = gamesList.size() - 1;
        for (Node child : gameHbox2.getChildren()) {
            if (child instanceof VBox) {
                VBox vbox = (VBox) child;
                for (Node grandChild : vbox.getChildren()) {
                    if (grandChild instanceof Text) {
                        String title = gamesList.get(iterator).getKey();
                        Text text = (Text) grandChild;
                        text.setText(gamesList.get(iterator).getKey());
                    } else if (grandChild instanceof ImageView) {
                        String imageName = gamesList.get(iterator).getValue();
                        URL url = getClass().getResource("assets/" + imageName);
                        ImageView imageView = (ImageView) grandChild;
                        imageView.setImage(new Image(url.toString()));
                    }
                }
            }
            iterator--;
        }
           
    }

    public void gotoGamePage(MouseEvent event) {
        // ------------- When a game is clicked, we first find out which game was it
        // ------------- then i pass on that title to gamepage in order to get the rest
        // of game data with the
        // ------------- help of that title

        Node node = (Node) event.getSource();
        mainMenu.setCurrentButton();
        String selectedGame = "";
        if (node instanceof VBox) {
            VBox vbox = (VBox) node;
            for (Node child : vbox.getChildren()) {
                if (child instanceof Text) {
                    Text text = (Text) child;
                    selectedGame = text.getText();
                    System.out.println("GAME: " + selectedGame);
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
            controller.setTitle(selectedGame);
            controller.setPrevScene("home");
            controller.run();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gotoStorePage(ActionEvent event) {
        mainMenu.gotoStorePage(event);
    }

}
