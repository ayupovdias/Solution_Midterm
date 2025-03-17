import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public interface PaymentMethod {
    String processPayment(double amount);
}
class CreditCardAPI{
    public boolean checkCard(String nums){
        if(nums.length()==16){
            return true;
        }
        return false;
    }
    public String processPayment(double amount){
        return "You paid "+amount+" tg using credit card";
    }
}
class PayPalAPI{
    public boolean checkPayPal(String email){
        if(email.contains("@") && email.contains(".")){
            return true;
        }
        return false;
    }
    public String processPayment(double amount){
        return "You paid "+amount+" tg using PayPal";
    }
}

class CryptoPaymentAPI{
    public boolean checkCrypto(String address){
        if(address.substring(0,2).equals("0x")){
            return true;
        }
        return false;
    }
    public String processPayment(double amount){
        return "You paid "+amount+" tg using crypto";
    }
}
class CreditCardAdapter implements PaymentMethod{
    CreditCardAPI creditCard;
    String nums;

    public CreditCardAdapter(String nums){
        creditCard=new CreditCardAPI();
        this.nums=nums;
    }
    public String processPayment(double amount){
        if(creditCard.checkCard(nums)){
            return creditCard.processPayment(amount);
        }
        return "Failure: Credit card doesn't consist of 16 figures. Try again";
    }
}
class PayPalAdapter implements PaymentMethod{
    PayPalAPI payPal;
    String email;
    public PayPalAdapter(String email){
        payPal=new PayPalAPI();
        this.email=email;
    }
    public String processPayment(double amount){
        if(payPal.checkPayPal(email)){
            return payPal.processPayment(amount);
        }
        return "Failure: Email doesn't contain '.' or '@'. Try again";
    }
}
class CryptoPaymentAdapter implements PaymentMethod{
    CryptoPaymentAPI cryptoPayment;
    String address;
    public CryptoPaymentAdapter(String data){
        cryptoPayment=new CryptoPaymentAPI();
        this.address=data;
    }
    public String processPayment(double amount){
        if(cryptoPayment.checkCrypto(address)){
            return cryptoPayment.processPayment(amount);
        }
        return "Failure: Address doesn't contain 0x. Try again";
    }
}
class PaymentFactory{
    public PaymentMethod create(String payment, String data){
        switch(payment.toLowerCase()){
            case "credit card":
                return new CreditCardAdapter(data);
            case "paypal":
                return new PayPalAdapter(data);
            case "crypto":
                return new CryptoPaymentAdapter(data);
            default:
                throw new IllegalArgumentException("Failure: you can choose only credit card, PayPal or crypto");
        }
    }
}
class TransactionHistory{
    private static List<String> transactions=new ArrayList<>();
    public void addTransaction(String transaction){
        transactions.add(transaction);
    }
    public void showHistory(){
        System.out.println("History of transactions: ");
        for(String i:transactions){
            System.out.println(i);
        }
    }
}
class Main{
    public static void main(String[] args){
        TransactionHistory transactionHistory=new TransactionHistory();
        Scanner scanner=new Scanner(System.in);
        PaymentFactory factory=new PaymentFactory();
        while(true) {
            System.out.println("What way of payment will you choose?");
            System.out.println("You can choose credit card, PayPal or crypto. If you want to quit, write 'quit'");
            String way = scanner.nextLine().trim();
            if(way.toLowerCase().equals("quit")){
                System.out.println("You have just quited");
                break;
            }
            switch (way.toLowerCase()) {
                case "credit card":
                    System.out.println("You have chosen credit card!");
                    System.out.println("Enter 16 figures");
                    break;
                case "paypal":
                    System.out.println("You have chosen PayPal!");
                    System.out.println("Enter your email");
                    break;
                case "crypto":
                    System.out.println("You have chosen crypto");
                    System.out.println("Enter your address (starts with 0x)");
                    break;
                default:
                    System.out.println("Error: you wrote incorrectly. Try again");
                    System.out.println();
                    continue;

            }
            String data=scanner.next();
            PaymentMethod payment=factory.create(way,data);
            System.out.println("Enter amount:");
            double amount=scanner.nextDouble();
            scanner.nextLine();
            String result=payment.processPayment(amount);
            String status;
            if(result.contains("Failure")){
                status="Status: Failure";
                result=result.substring(9);
            }
            else{
                status="Status: Completed";
            }
            System.out.println(status);
            System.out.println(result);
            transactionHistory.addTransaction(status+". "+result);
            System.out.println();
        }
        transactionHistory.showHistory();

    }
}
