package App.BLL;

import App.Database.DBHandler.CreditCardHandler;

public class CreditCard implements Payment {

    CreditCardHandler creditCardHandler;

    public CreditCard() {
        creditCardHandler = new CreditCardHandler();
    }

    @Override
    public void deductCharges(int gameID, int userID) {
        creditCardHandler.deductCharges(gameID, userID);
    }
    
}
