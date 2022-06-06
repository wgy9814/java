package com.wgy.smallchange.oop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSysOOP {

    //属性
    boolean loop = true;//判断是否退出
    Scanner scanner = new Scanner(System.in);
    String key = "";

    String details = "=========零钱通明细=========";
    double money = 0;
    double balance = 0;//收益

    Date date = null;
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    //上面代码是用来日期格式化的

    String note = "";//消费原因

    //菜单
    public void mainMenu() {
        System.out.println("显示菜单");
        do {
            System.out.println("\n=========零钱通菜单=========");
            System.out.println("\t\t1.零钱通明细");
            System.out.println("\t\t2.收益入账");
            System.out.println("\t\t3.消费情况");
            System.out.println("\t\t4.退   出");

            System.out.println("请选择1-4");
            key = scanner.next();

            switch (key){
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("重写选择");
            }

        }while (loop);
    }

    //明细
    public void detail() {
        System.out.println(details);
    }

    //收益入账
    public void income() {
        System.out.println("收益入账金额");
        money = scanner.nextDouble();

        if (money <= 0) {
            System.out.println("收益金额应大于0");
            return;
        }

        balance += money;
        date = new Date();

        details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
    }

    //消费
    public void pay() {
        System.out.println("消费金额");
        money = scanner.nextDouble();

        if (money <= 0 || money > balance) {
            System.out.println("消费金额 范围 应该在0 - " + balance);
            return;
        }

        balance -= money;
        System.out.println("消费说明");
        note = scanner.next();
        date = new Date();

        details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;

    }

    //退出
    public void exit() {
        String choice = "";

        while (true) {
            System.out.println("确认退出吗？ y/n");
            choice = scanner.next();
            if ("y".equals(choice) || "n".equals(choice)) {
                break;
            } else {
                System.out.println("重新确认");
            }
        }

        if (choice.equals("y")) {
            loop = false;
        }
    }
}