package chapter21.src.com.wgy.QQclient.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:23
 */
//UserClientService类

import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;
import chapter21.src.com.wgy.QQclient.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

//UserClientService类
/**
 * 该类完成用户登录验证和用户注册功能等
 */
public class UserClientService {

    //创建User属性,因为可能在其他地方使用User信息
    // 在User类中创建一个无参构造器
    private User u = new User();
    //因为Socket在其他地方也可能使用到，因此做成属性
    private Socket socket;

    //根据userId 和 pwd 到服务器端验证用户是否合法
    public boolean checkUser(String userId ,String pwd) throws Exception{
        boolean b = false;
        //创建User对象
        u.setUserId(userId);
        u.setPasswd(pwd);
        //连接到服务端，发送u对象

        socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        //得到ObjectOutputStream对象
        ObjectOutputStream oos =
                new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);//发送User对象

        //读取服务器返回的Message对象
        ObjectInputStream ois =
                new ObjectInputStream(socket.getInputStream());
        Message ms = (Message)ois.readObject();

        if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){//登陆成功

            //创建一个和服务器端保持通信的线程 -> 创建一个类， ClientConnectServerThread
            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
            //启动线程
            clientConnectServerThread.start();
            //客户端可能会有多个Socket线程，为了方便管理和扩展，可以放在集合中。
            ManageClientConnectServerThread.addClientConnectServerThread(userId,clientConnectServerThread);
            b = true;
        }else {
            //登陆失败,不能启动和服务器通信的线程，关闭Socket
            socket.close();
        }
        return b;
    }
    //向服务器端请求在线用户列表
    public void onlineFriendList(){
        //发送一个message，类型MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setSender(u.getUserId());
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        //发送给服务器
        try {
            //从管理线程的集合中，通过userId，得到这个线程
            ClientConnectServerThread clientConnectServerThread =
                    ManageClientConnectServerThread.getClientConnectServerThread(u.getUserId());
            //得到这个线程关联的socket
            Socket socket = clientConnectServerThread.getSocket();
            //得到当先socket对应的ObjectOutPutStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //向服务器端发送message对象，要求服务器要求在线用户列表
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //编写方法，退出客户端，并给服务端发送一个退出系统的message对象
    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());//指出是哪个用户id

        //发送message
        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(message);
            System.out.println(u.getUserId() + " 退出系统");
            System.exit(0);//结束进程
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}