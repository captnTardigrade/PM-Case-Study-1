import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

interface Bank {
    boolean isCorrectPIN();

    boolean isValidCard(String cardNumber);

    boolean hasEnoughBalance(long id, double bill);
}

public class BankTen implements Bank {

    @Override
    public boolean isCorrectPIN() {
        double i = Math.random();
        if (i >= 0.98) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidCard(String cardNumber) {
        String validCardPattern = "^4[0-9]{12}(?:[0-9]{3})?$";
        Pattern pattern = Pattern.compile(validCardPattern);
        if (pattern.matcher(cardNumber).matches()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasEnoughBalance(long id, double bill) {
        boolean found = false;
        double balance = 0;
        long ID;
        Scanner scanner;

        try {
            scanner = new Scanner(new File("Bank.csv"));
            scanner.useDelimiter("[,\n]");
            while (scanner.hasNext() && !found) {
                ID = scanner.nextLong();
                balance = scanner.nextDouble();
                if(ID == id){
                    if(balance>=bill){
                        return true;
                    }
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}