package App.BLL;

import java.nio.file.Files;
import java.nio.file.Paths;

import App.Database.DBHandler.PublishGameHandler;

public class PublishGame {

    PublishGameHandler publishGameHandler;

    public PublishGame() {
        publishGameHandler = new PublishGameHandler();
    }

    public boolean publish(String title , String description, String genre, String platform, String price, String releaseDate, String developer, String publisher, String filePath)
    {
        if(!inputCheck(title, description, genre, platform, price, releaseDate, developer, publisher, filePath))
            return false;

        return publishGameHandler.addGame(title, description, genre, platform, price, releaseDate, developer, publisher, filePath);
    }

    private boolean inputCheck(String title , String description, String genre, String platform, String price, String releaseDate, String developer, String publisher, String filePath)
    {
        if(title.isEmpty() || description.isEmpty() || genre.isEmpty() || platform.isEmpty() || price.isEmpty() || releaseDate.isEmpty() || developer.isEmpty() || publisher.isEmpty() || filePath.isEmpty())
            {
                System.out.println("Please fill in all fields");
                return false;
            }

    //      // Check if file exists
    // if (!Files.exists(Paths.get(filePath))) {
    //     System.out.println("File does not exist at the provided path");
    //     return false;
    // }
        return true;
    }
    
    
}
