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
       // spawnTestAccount();
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
            System.out.println("按回车进入下一步或确定");
            scanner.nextLine();
            scanner.nextLine();
            System.out.println("==========================");
            System.out.println("您已进入操作系统，可以选择办理以下业务");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");
            switch (scanner.next()) {
                case "1":
                    checkAccount(account);
                    break;
                    case "2":
                        Deposit(account);

                        break;
                        case "3":
                            Withdraw(account);

                            break;
                            case "4":
                                Transfer(account);

                                break;
                                case "5":
                                    ChangePassword(account);

                                    break;
                                    case "6":
                                        return;
                                        case "7":
                                            deleteAccount(account);
                                            return;

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
    }
    private void Deposit(Account account){
        System.out.println("请输入存入金额");
        account.setBalance(account.getBalance()+scanner.nextDouble());
        System.out.println("存款成功,目前余额"+account.getBalance()+"元");
    }
    private void Withdraw(Account account){
        while (true) {
            System.out.println("请输入取款金额（至少100元）");
            double withdrawNumber = scanner.nextDouble();
            if (withdrawNumber<100){
                System.err.println("取款金额最低为100元");}
            else {
                if (withdrawNumber>account.getWithdrawLimit()){
                    System.err.println("超过取款限额");
                }
                else {
                    if(withdrawNumber>account.getBalance()){
                        System.err.println("余额不足");
                    }
                    else{
                    account.setBalance(account.getBalance()-withdrawNumber);
                    System.out.println("成功取出"+withdrawNumber+"元,目前余额为"+account.getBalance()+"元");
                    break;
                    }}
            }
        }
    }
    private void Transfer(Account account){
        System.out.println("请输入目标账户用户名");
        String targetUserName;
        double TransferAmount;
        while (true) {
            targetUserName = scanner.next();
            if(!existUserName(targetUserName)){
                System.err.println("未查询到此账户");
                System.out.println("请重新输入账户");
            }
            else {break;}
        }
        while (true) {
            System.out.println("请输入转账金额");
             TransferAmount=scanner.nextDouble();
            if (TransferAmount>account.getBalance()){
                System.err.println("余额不足");
            }
            else {
                if (TransferAmount>account.getWithdrawLimit()){
                    System.err.println("转账金额超出限额");
                }
                else {break;}
            }
        }
        getObejctbyUsername(targetUserName).setBalance(getObejctbyUsername(targetUserName).getBalance()+TransferAmount);
        account.setBalance(account.getBalance()-TransferAmount);
        System.out.println("成功给卡号"+getObejctbyUsername(targetUserName).getCardID()+"转账"+TransferAmount+"元。");

    }
    private void ChangePassword(Account account){
        System.out.println("请输入当前密码");
        String inputPassword = scanner.next();
        if (inputPassword.equals(account.getPassword())){
            System.out.println("请输入更改的密码");
            String newPassword = scanner.next();
            if (newPassword.equals(account.getPassword())){
                System.err.println("两次密码重合");
                return;
            }
            System.out.println("请再次输入更改的密码");
            String secondPassword = scanner.next();
            if (newPassword.equals(secondPassword)){
                account.setPassword(newPassword);
                System.out.println("更改成功");
            }
            else{
                System.err.println("两次密码不一致");
            }
        }
        else {
            System.err.println("当前密码输入错误");
        }
    }
    private void deleteAccount(Account account){
        System.out.println("请输入当前账户密码");
        if (scanner.next().equals(account.getPassword())){
            System.err.println("您即将删除您的账户，请输入“我确定删除该账户”");
            if (scanner.next().equals("我确定删除该账户")){
                System.err.println("成功删除"+accountList.remove(account));
            }
        }
    }
    }



