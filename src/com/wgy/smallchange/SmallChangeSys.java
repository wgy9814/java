package com.wgy.smallchange;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSys {

    public static void main(String[] args) {

        double money = 0.0;
        double balance = 0.0;//余额

        String note = "";//消费原因

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm" );
        //上面代码是用来日期格式化的
        //在这里不能new Date，否则日期将会确定，不会改变

        boolean loop = true;//判断是否退出零钱通系统

        //完成零钱通明细
        //老韩建议最好不使用数组，因为不知道入账和消费的次数，不能确定数组的大小
        String details = "=========零钱通明细=========";

        Scanner scanner = new Scanner(System.in);
        String key = "";
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
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额");
                    money = scanner.nextDouble();

                    if (money <= 0){//对金额的校验，收益不可能为负数
                        System.out.println("收益金额应大于0");
                        break;
                    }

                    balance += money;

                    date = new Date();
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                //用字符串拼接来输出结果

                case "3":
                    System.out.println("消费金额");
                    money = scanner.nextDouble();

                    if (money <= 0 || money > balance){
                        System.out.println("消费金额 范围 应该在0 - " + balance);
                        break;
                    }
                    //尽量避免出现else，因为else会将代码分成两部分
                    //可读性和维护性不高，不到万不得已最好不使用else

                    balance -= money;
                    System.out.println("消费说明");
                    note = scanner.next();
                    date = new Date();

                    details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;

                    break;
                case "4":
                    String choice = "";

                    while (true){
                        System.out.println("确认退出吗？ y/n");
                        choice = scanner.next();
                        if ("y".equals(choice) || "n".equals(choice)){
                            break;
                        }else {
                            System.out.println("重新确认");
                        }
                    }

                    if (choice.equals("y")){
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("重写选择");
            }

        }while (loop);

        System.out.println("==========退出零钱通=========");
    }
}