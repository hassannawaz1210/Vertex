package App.BLL;

import java.util.ArrayList;
import java.util.Map;

import App.Database.DBHandler.HomeHandler;

public class Home {

    private HomeHandler homeHandler;
    private ArrayList<Map.Entry<String, String>> gamesList;

    public Home() {
        homeHandler = new HomeHandler();
    }

    public ArrayList<Map.Entry<String, String>> getGames() {
        gamesList = homeHandler.getGames();
        return gamesList;
    }
    
}
