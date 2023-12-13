package App.BLL;

import App.Database.DBHandler.ReportHandler;

public class Report {

    ReportHandler reportHandler;

    public Report() {
        reportHandler = new ReportHandler();
    }

    public boolean submitReport(String reason, String attachment, int gameID , int userID) {
        reportHandler.addReport(reason, attachment, gameID, userID);
        return true;
    }

    public String getGameTitle(int gameID) {
        return reportHandler.getGameTitle(gameID);
    }
    
}
