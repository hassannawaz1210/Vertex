package App.UI;

import App.BLL.CreditCard;
import App.BLL.GamePageBLL;
import App.BLL.Payment;
import App.BLL.PaymentFactory;
import App.BLL.User;
import App.Database.*;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameController {

    @FXML
    private Text Platform;

    @FXML
    private ImageView coverPicture;

    @FXML
    private Text developer;

    @FXML
    private Text price;

    @FXML
    private Text publisher;

    @FXML
    private Text releaseDate;

    @FXML
    private Text title;

    @FXML
    private TextField starsField;

    @FXML
    private TextArea commentField;

    @FXML
    private VBox reviewContainer;

    @FXML
    private BorderPane borderPane;

    // -------- my variables
    int gameID;

    private Database db = Database.getInstance();
    private User user = User.getInstance();
    private MainMenu mainMenu = MainMenu.getInstance();
    private String previousScene;
    private GamePageBLL gamePageBLL = new GamePageBLL();
    private Payment creditCard = PaymentFactory.createPaymentType("CreditCard");
    private TitleBar titleBar;

    // =================== implementation ===================
    public void run() {
        titleBar = new TitleBar("Game", mainMenu.getMainStage());
        borderPane.setTop(titleBar.getRoot());
        borderPane.setLeft(mainMenu.getRoot());
        updatePage();
        displayReviews();
    }

    private void updatePage() {
        Connection conn = db.getConnection();
        String title = this.title.getText();
        String query = "SELECT * FROM game WHERE title = '" + title + "'";
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                gameID = rs.getInt("gameID");
                Platform.setText(rs.getString("platform"));
                developer.setText(rs.getString("developer"));
                publisher.setText(rs.getString("publisher"));
                releaseDate.setText(rs.getString("releaseDate"));
                price.setText(rs.getString("price"));
                String imageName = rs.getString("filePath");
                URL url = getClass().getResource("assets/" + imageName);
                Image image = new Image(url.toExternalForm());
                coverPicture.setImage(image);
                // displayFields();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void displayFields() {
        System.out.println("title: " + title.getText());
        System.out.println("Platform: " + Platform.getText());
        System.out.println("developer: " + developer.getText());
        System.out.println("publisher: " + publisher.getText());
        System.out.println("releaseDate: " + releaseDate.getText());
        System.out.println("price: " + price.getText());
        System.out.println("filePath: " + coverPicture.getImage().getUrl());
    }

    @FXML
    void goBack(ActionEvent event) {
        System.out.println("go back");
        if (previousScene.equals("home")) {
            mainMenu.gotoHomePage(event);
        } else if (previousScene.equals("store")) {
            mainMenu.gotoStorePage(event);
        }
    }

    @FXML
    void submitReview(ActionEvent event) {
        String stars = starsField.getText();
        String comment = commentField.getText();
        String query = "INSERT INTO review (stars, comment, timestamp, gameID, userID) VALUES ('" + stars + "', '"
                + comment + "', NOW(), '" + gameID + "', '" + user.getUserID() + "')";
        Connection conn = db.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Review submitted");
            displayReviews();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    void gotoReportPage(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("report.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            // set current controller as setUserData of new scene
            scene.setUserData(this);
            ReportController controller = loader.getController();
            controller.setGameID(gameID);
            controller.run();
            controller.setPrevScene(previousScene);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            // stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void purchaseGame(ActionEvent event) {
        if (gamePageBLL.isCardAttached()) {
            if (gamePageBLL.isAlreadyBought(gameID)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Payment");
                alert.setHeaderText(null);
                alert.setContentText("You already own this game.");
                alert.showAndWait();

            } else {

                // Create a ChoiceDialog
                // List<CreditCard> creditCards = null;// replace with your method to get credit cards
                // ChoiceDialog<CreditCard> dialog = new ChoiceDialog<>(null, creditCards);
                // dialog.setTitle("Select Credit Card");
                // dialog.setHeaderText(null);
                // dialog.setContentText("Choose your credit card:");

                // // Show the ChoiceDialog and get the result
                // Optional<CreditCard> result = dialog.showAndWait();
                // result.ifPresent(creditCard -> System.out.println("Selected credit card: " + creditCard));

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Payment");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Are you sure you want to purchase this game?\nAn amount will be deducted from your account.");
                // buttons
                ButtonType buttonTypeYes = new ButtonType("Yes");
                ButtonType buttonTypeNo = new ButtonType("Cancel");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                if (alert.showAndWait().get() == buttonTypeYes) {
                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setTitle("Purchase");
                    alert2.setHeaderText(null);
                    alert2.setContentText("You have successfully purchased this game.");
                    alert2.showAndWait();
                    creditCard.deductCharges(gameID, user.getUserID());
                    gamePageBLL.addToLibrary(gameID);
                } else {
                    System.out.println("Purchase cancelled");
                }

                return;
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Payment");
            alert.setHeaderText(null);
            alert.setContentText("You have no card attached to your account.");
            alert.showAndWait();
        }
    }

    private void displayReviews() {
        reviewContainer.getChildren().clear();
        Connection conn = db.getConnection();
        String query = "SELECT * FROM review WHERE gameID = '" + gameID + "'";
        try {
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            VBox currentReview = null;

            // if no reviews exist
            // if rs is empty

            if (!rs.next()) {
                VBox review = new VBox();
                review.setId("reviewBg");
                review.setPrefWidth(Region.USE_COMPUTED_SIZE);
                review.setPrefWidth(Region.USE_COMPUTED_SIZE);
                review.setAlignment(Pos.CENTER);
                Text text = new Text("No reviews yet");
                text.setFill(Color.WHITE);
                review.getChildren().add(text);
                reviewContainer.getChildren().add(review);
                return;
            }

            do {

                currentReview = addReview();
                String stars = rs.getString("stars");
                String comment = rs.getString("comment");
                String timestamp = rs.getString("timestamp");
                int userID = rs.getInt("userID");
                String query2 = "SELECT name FROM user WHERE userID = '" + userID + "'";
                java.sql.Statement stmt2 = conn.createStatement();
                java.sql.ResultSet rs2 = stmt2.executeQuery(query2);
                if (rs2.next()) {
                    String name = rs2.getString("name");

                    for (Node child : currentReview.getChildren()) {
                        if (child instanceof VBox) {
                            VBox vbox = (VBox) child;
                            Text user = (Text) vbox.getChildren().get(0);
                            user.setText(name);
                            Text date = (Text) vbox.getChildren().get(1);
                            date.setText(timestamp);
                            Text strs = (Text) vbox.getChildren().get(2);
                            strs.setText("Stars: " + stars);
                        } else if (child instanceof Text) {
                            Text text = (Text) child;
                            text.setText("Comment: " + comment);
                        }
                    }
                }
                reviewContainer.getChildren().add(currentReview);
            } while (rs.next());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private VBox addReview() {
        VBox review = new VBox();
        review.setId("reviewBg");
        review.setPrefWidth(Region.USE_COMPUTED_SIZE);
        review.setPrefWidth(Region.USE_COMPUTED_SIZE);
        review.setSpacing(5);
        VBox header = new VBox();
        header.setPrefWidth(Region.USE_COMPUTED_SIZE);
        header.setPrefWidth(Region.USE_COMPUTED_SIZE);
        Text user = new Text("");
        Text date = new Text("");
        Text stars = new Text("");
        user.setFill(Color.WHITE);
        date.setFill(Color.WHITE);
        stars.setFill(Color.WHITE);
        user.setStyle("-fx-font-weight: bold");
        date.setStyle("-fx-font-weight: bold");
        Text comment = new Text("");
        comment.setFill(Color.WHITE);

        header.getChildren().add(user);
        header.getChildren().add(date);
        header.getChildren().add(stars);
        review.getChildren().add(header);
        review.getChildren().add(comment);
        return review;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setPrevScene(String scene) {
        this.previousScene = scene;
    }

}
