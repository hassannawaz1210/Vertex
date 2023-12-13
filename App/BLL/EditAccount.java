package App.BLL;

import App.Database.DBHandler.EditAccountHandler;


public class EditAccount {
    EditAccountHandler editAccoutHandler;

    public EditAccount() {
        editAccoutHandler = new EditAccountHandler();
    }

    public void updateBio(String bio)
    {
        editAccoutHandler.updateBio(bio);
    }

    public void updatePassword(String password)
    {
        editAccoutHandler.updatePassword(password);
    }

    public void updateEmail(String email)
    {
        editAccoutHandler.updateEmail(email);
    }

    public void updateName(String name)
    {
        editAccoutHandler.updateName(name);
    }

    public void updateUsername(String username)
    {
        editAccoutHandler.updateUsername(username);
    }


}
