package App.BLL;

public class PaymentFactory {

    private static PaymentFactory paymentFactory = null;

    private PaymentFactory() {
    }

    public static Payment createPaymentType(String paymentType) {
        if (paymentType == null) {
            return null;
        }
        if (paymentType.equalsIgnoreCase("CreditCard")) {
            return new CreditCard();
        } 
        // else if (paymentType.equalsIgnoreCase("debitcard")) {
        //     return new DebitCard();
        // }
        return null;
    }

    public static PaymentFactory getInstance() {
        if (paymentFactory == null) {
            paymentFactory = new PaymentFactory();
        }
        return paymentFactory;
    }
    
}
