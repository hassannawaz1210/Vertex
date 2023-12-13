package App.BLL;

import App.Database.DBHandler.LibraryHandler;
import java.util.ArrayList;
import java.util.Map;


public class Library {
    LibraryHandler libraryHandler;
    ArrayList<Map.Entry<String, String>> gamesList;

    public Library() {
        libraryHandler = new LibraryHandler();
    }

    public ArrayList<Map.Entry<String, String>> getGames() {
        gamesList = libraryHandler.getGames();
        return gamesList;
    }
}
