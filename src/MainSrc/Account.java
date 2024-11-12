package MainSrc;

public class Account {
    private String cardID;
    private String userName;
    private char sex;
    private String password;
    private double balance;
    private double withdrawLimit;

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public Account() {
    }

    public Account(String accountNumber, String name, char gender, String password, double balance, double withdrawLimit) {
        this.cardID = accountNumber;
        userName = name;
        sex = gender;
        this.password = password;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }
}
