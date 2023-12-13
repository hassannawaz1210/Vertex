package App.BLL;

import java.util.ArrayList;

import App.Database.DBHandler.LoginHandler;
import javafx.scene.control.Alert;

public class Login {

    private LoginHandler loginHandler;
    private User user;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    public Login() {
        loginHandler = new LoginHandler();
        user = User.getInstance();
    }

    public boolean login(String email, String password) {
        if(!inputCheck(email, password))
            return false;
        if(!verifyCredentials(email, password))
            return false;

        updateUser(email, password);
        return true;
    }

    private boolean verifyCredentials(String email, String password) {
        if(loginHandler.exists(email, password))
            return true;
        else
            return false;
    }
    
    
    private boolean inputCheck(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            System.out.println("Please fill in all fields");
            return false;
        } else if (!email.matches(EMAIL_REGEX)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email");
            alert.showAndWait();
            System.out.println("Please enter a valid email");
            return false;
        } 
        return true;
    }

    private void updateUser(String email, String password) {
        ArrayList<String> userData = loginHandler.getUserData(email, password);
        user = User.getInstance();
        user.setUserID(Integer.parseInt(userData.get(0)));
        user.setEmail(userData.get(1));
        user.setName(userData.get(2));
        user.setUsername(userData.get(3));
        user.setType(userData.get(4));
    }

}
