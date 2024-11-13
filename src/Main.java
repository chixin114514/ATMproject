import MainSrc.ATM;
import MainSrc.Account;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        while (true) {
            atm.start();
        }
    }
}