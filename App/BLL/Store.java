package App.BLL;

import java.util.ArrayList;
import java.util.Map;

import App.Database.DBHandler.StoreHandler;


public class Store {

    StoreHandler storeHandler;
    ArrayList<Map.Entry<String, String>> gamesList;

    public Store() {
        storeHandler = new StoreHandler();
    }
    
    public ArrayList<Map.Entry<String, String>> getGames()
    {
        gamesList = storeHandler.getGames();
        return gamesList;
    }

}
