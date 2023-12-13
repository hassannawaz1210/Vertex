package App.BLL;

public class User {

    private static User instance;

    int userID;
    private String email;
    private String name;
    private String username;
    private String accType;
    
    private User(){}

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return accType;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setType(String accType) {
        this.accType = accType;
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
}
