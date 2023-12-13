package App.BLL;

import App.Database.DBHandler.SignUpHandler;

public class SignUp {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private SignUpHandler signUpHandler;

    public SignUp() {
        signUpHandler = new SignUpHandler();
    }

    public boolean signUp(String email, String password, String name, String username, String accType) {
        if (inputCheck(email, password, name, username, accType) == false || alreadyExists(email, username))
            return false;

        signUpHandler.addUser(email, password, name, username, accType);
        System.out.println("Account created successfully");
        return true;
    }

    private boolean alreadyExists(String email, String username) {
        if (signUpHandler.exists(email, username)) {
            System.out.println("Email or username already exists");
            return true;
        } else
            return false;
    }

    private Boolean inputCheck(String email, String password, String name, String username, String accType) {
        if (email.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty() || accType.isEmpty()) {
            System.out.println("Please fill all the fields");
            return false;
        } else if (!email.matches(EMAIL_REGEX)) {
            System.out.println("Please enter a valid email");
            return false;
        } else if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        } else if (accType.equals("Select account type")) {
            System.out.println("Please select an account type");
            return false;
        }

        return true;

    }

}
