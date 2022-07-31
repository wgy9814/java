package chapter21.src.com.wgy.QQclient.view;

import chapter21.src.com.wgy.QQclient.service.FileClientService;
import chapter21.src.com.wgy.QQclient.service.MessageClientService;
import chapter21.src.com.wgy.QQclient.service.UserClientService;
import utils.Utility;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:17
 */
//QQView类
/**
 *用户界面，一级二级菜单
 */
public class QQView {

    private boolean loop = true;//控制是否显示菜单
    private String key = ""; //接受用户的键盘输入
    private UserClientService userClientService = new UserClientService();//对象是用于登录服务/注册用户
    private MessageClientService messageClientService = new MessageClientService();//对象用户私聊/群聊
    private FileClientService fileClientService = new FileClientService();//对象用于传输文件



    public static void main(String[] args) throws Exception {
        new QQView().mainMenu();
        System.out.println("客户端退出系统");
    }
    //显示主菜单
    private void mainMenu() throws Exception {
        while (loop){
            System.out.println("=========欢迎登录网络通信系统=========");
            System.out.println("\t\t1 登录系统");
            System.out.println("\t\t9 退出系统");

            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            //根据用户的输入，处理不同的逻辑
            switch (key){
                case "1":
                    System.out.print("请输入用户号：");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密  码：");
                    String pwd = Utility.readString(50);
                    //需要到服务端验证该用户是否合法（即是否存在该用户）
                    if(userClientService.checkUser(userId,pwd)){//假设合法
                        System.out.println("=========欢迎用户 "+userId+" 登录成功=========");
                        //进入二级菜单
                        while (loop){
                            System.out.println("=========网络通信系统二级菜单（用户 "+userId+" ）=========");
                            System.out.println("\t\t1 显示在线用户列表");
                            System.out.println("\t\t2 群发消息");
                            System.out.println("\t\t3 私聊消息");
                            System.out.println("\t\t4 发送文件");
                            System.out.println("\t\t9 退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    userClientService.onlineFriendList();
//                                    System.out.println("显示在线用户列表");
                                    break;
                                case "2":
                                    System.out.print("请输入要群聊的内容：");
                                    String sendAllContent = Utility.readString(100);
                                    messageClientService.sendPublicMessage(userId,sendAllContent);
                                    break;
                                case "3":
                                    System.out.print("请输入要私聊的姓名（在线的）：");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入要私聊的内容：");
                                    String sendContent = Utility.readString(100);
                                    messageClientService.sendPrivateMessage(userId,getterId,sendContent);
                                    System.out.println("私聊消息");
                                    break;
                                case "4":
                                    System.out.print("请输入要发送给谁文件（在线的）：");
                                    String getterFileId = Utility.readString(50);
                                    System.out.print("请输入文件的源路径：");
                                    String fileSrc = Utility.readString(100);
                                    System.out.print("请输入文件保存的存储路径：");
                                    String fileDesc = Utility.readString(100);
                                    fileClientService.sendFile(userId,getterFileId,fileSrc,fileDesc);
                                    break;
                                case "9":
                                    //调用方法，给服务器发送一个退出系统的message
                                    userClientService.logout();
                                    loop = false;
//                                    System.out.println("退出系统");
                                    break;
                            }
                        }
                    }else {//登录服务器失败
                        System.out.println("========登陆失败========");
                    }
                    break;
                case "9":
                    loop = false;
//                    System.out.println("退出系统");
                    break;

            }
        }
    }
}