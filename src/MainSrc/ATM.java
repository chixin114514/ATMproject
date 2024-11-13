package MainSrc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accountList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private boolean existCardID(String cardID){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getCardID().equals(cardID)) {
                return true;
            }
        }
        return false;
    }
    private boolean existUserName(String username){
        for (int i = 0; i < accountList.size(); i++) {
            if (accountList.get(i).getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
    private Account getObejctbyUsername(String Username){
        for (int i = 0; i < accountList.size(); i++) {
            if(accountList.get(i).getUserName().equals(Username)){
                return accountList.get(i);
            };
        }
        return null;
    }
    private void spawnTestAccount(){
        Account account = new Account();
        account.setUserName("test");
        account.setPassword("test");
        account.setCardID("12345678");
        account.setSex('男');
        account.setWithdrawLimit(1000);
        account.setBalance(10000);
        accountList.add(account);
    }


    //主程序
    public void start(){
        spawnTestAccount();
        System.out.println("==ATM系统==");
        System.out.println("0、退出系统");
        System.out.println("1、用户登录");
        System.out.println("2、用户开户");
        System.out.println("请选择您要操作的命令：");
        switch (scanner.nextInt()){
            case 0:
                System.exit(0);
            case 1:
                login();
                return;
            case 2:
                register();
                break;
            default:
                System.err.println("未定义的选项");
                break;
        }
    }
    private void register(){
        Account account = new Account();
        System.out.println("==开户系统操作==");
        System.out.println("请输入您的用户名");
        account.setUserName(scanner.next());
        if(existUserName(account.getUserName())){
            System.err.println("已存在该用户名");
            return;
        }
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
        account.setBalance(0);

        accountList.add(account);
        System.out.println("已成功注册新账户,卡号为"+account.getCardID()+"。");
    }
    private void login(){
        if(accountList.size()==0){
            System.err.println("数据库暂无账号");
            return;
        }
        String logInUserName;
        System.out.println("======系统登录操作======");

        while (true) {
            System.out.println("请输入用户名称");
            logInUserName = scanner.next();
            if(!existUserName(logInUserName)){
                System.err.println("未查找到该用户");
            }
            else {break;}
        }
        System.out.println("请输入登录密码");
        String password = scanner.next();

        for (int tries =0;tries<3;tries++) {
            if(!password.equals(getObejctbyUsername(logInUserName).getPassword())){
                System.err.println("密码错误，你还有"+(3-tries)+"次尝试机会");
                password = scanner.next();
            }else {
                System.out.println("账号"+ getObejctbyUsername(logInUserName).getCardID()+"登陆成功");
                operatingSystem(getObejctbyUsername(logInUserName));
                return;
            }
        }
        System.err.println("您已超过尝试次数");

    }
    private void operatingSystem(Account account) {
        while (true) {
            System.out.println("==========================");
            System.out.println("您已进入操作系统，可以选择办理以下业务");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            switch (scanner.nextInt()) {
                case 1:
                    checkAccount(account);
                    break;
                    case 2:
                        Deposit(account);
                        break;
                        case 3:
                            Withdraw(account);
                            break;
                            case 4:
                                Transfer(account);
                                break;
                                case 5:
                                    ChangePassword(account);
                                    break;
                                    case 6:
                                        return;
                                        case 7:
                                            Logout(account);
                                            default:
                                                System.err.println("无效操作");
                                                break;


            }
        }
    }

    //OS系统操作
    private void checkAccount(Account account) {
        System.out.println("====查询账户服务====");
        System.out.println("账户卡号："+account.getCardID());
        System.out.println("账户用户名："+account.getUserName());
        System.out.println("户主性别："+account.getSex());
        System.out.println("账户余额："+account.getBalance());
        System.out.println("账户提现额度："+account.getWithdrawLimit());
        System.out.println("=====查询结束=====");
        scanner.nextLine();
    }
    private void Deposit(Account account){
        System.out.println("请输入存入金额");
        account.setBalance(account.getBalance()+scanner.nextDouble());
    }
    private void Withdraw(Account account){}
    private void Transfer(Account account){}
    private void ChangePassword(Account account){}
    private void Logout(Account account){}
    }



