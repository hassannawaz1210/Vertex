package App.BLL;

import App.Database.DBHandler.CreditCardHandler;
import App.Database.DBHandler.GameHandler;

public class GamePageBLL {

    GameHandler gameHandler;
    CreditCardHandler creditCardHandler;

    public GamePageBLL() {
        gameHandler = new GameHandler();
    }

    public boolean isCardAttached()
    {
        return gameHandler.isCardAttached();
    }

    public boolean isAlreadyBought(int gameID)
    {
        return gameHandler.isAlreadyBought(gameID);
    }

    public void addToLibrary(int gameID)
    {
        gameHandler.addToLibrary(gameID);
    }
}
