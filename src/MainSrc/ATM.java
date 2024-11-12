package MainSrc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accountList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private Account account = new Account();

    public void start(){
        System.out.println("==ATM系统==");
        System.out.println("1、用户登录");
        System.out.println("2、用户开户");
        System.out.println("请选择您要操作的命令：");
        switch (scanner.nextInt()){
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            default:
                System.err.println("未定义的选项");
                break;
        }
    }
    public void register(){
        System.out.println("==开户系统操作==");
        System.out.println("请输入您的用户名");
        account.setUserName(scanner.next());
        while (true) {
            System.out.println("请输入您的性别");
            char gender = scanner.next().charAt(0);
            if(gender == '男'||gender=='女'){
                account.setSex(gender);
                break;
            }
            else{
                System.err.println("性别不正确请重新输入");
            }
        }//获取性别
        while (true) {
            System.out.println("请输入账户密码");
            String password = scanner.next();
            System.out.println("请再次输入账户密码");
            String secondPW = scanner.next();
            if(password.equals(secondPW)){
                account.setPassword(password);
                break;
            }
            else {
                System.err.println("两次输入密码不一致");
            }
        }//获取密码
        System.out.println("请您输入您的取现额度");
        account.setWithdrawLimit(scanner.nextDouble());
        String cardID = "";
        do {
            cardID = "";
            for (int i = 0; i < 8; i++) {
                cardID += random.nextInt(10);
                account.setCardID(cardID);
            }
        } while (existCardID(cardID));
        accountList.add(account);
        System.out.println("已成功注册新账户,卡号为"+account.getCardID()+"。");
    }
    public void login(){

    }
    private void createCardID(){

    }
    public boolean existCardID(String cardID){
        return existCardID(cardID,accountList);
    }
    private boolean existCardID(String cardID,ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
             if (accounts.get(i).getCardID().equals(cardID)) {
                 return true;
             }
        }
        return false;
    }
}
