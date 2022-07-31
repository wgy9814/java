package chapter21.src.com.wgy.QQserver.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:54
 */
//QQServer类

//同一个包下的 共用 QQclient.qqcommon  不然会报错
import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;
import chapter21.src.com.wgy.QQclient.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

//QQServer类
/**
 * 服务端，在监听9999，等待客户端的连接，保持通信
 */
public class QQServer {

    private ServerSocket ss = null;//因为后面会用到，所以定义为一个属性
    //创建一个集合，存放多个用户，如果是这些用户登录，就认为是合法登录
    //这里我们也可以使用 ConcurrentHashMap, 可以处理并发的集合，没有线程安全
    //HashMap 没有处理线程安全，因此在多线程情况下是不安全
    //ConcurrentHashMap 处理的线程安全,即线程同步处理, 在多线程情况下是安全
    private static HashMap<String,User> validUsers = new HashMap<>();
    static { //初始化静态代码块，初始化 validUsers
        validUsers.put("100",new User("100","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("300",new User("300","123456"));
        validUsers.put("至尊宝",new User("至尊宝","123456"));
        validUsers.put("紫霞仙子",new User("紫霞仙子","123456"));
        validUsers.put("菩提老祖",new User("菩提老祖","123456"));
    }

    //验证用户是否有效的方法
    private boolean checkUser(String userId,String pwd){
//        for (int i = 0; i < validUsers.size(); i++) {
//            User user = validUsers.get(i);
//            if (userId.equals(user.getUserId()) && pwd.equals(user.getPasswd())){
//                return true;
//            }
//        }
//        return false;
        User user = validUsers.get(userId);
        if (user == null){//不存在直接返回false
            return false;
        }
        if (!(pwd.equals(user.getPasswd()))){//id正确，但是密码错误
            return false;
        }
        return true;
    }

    public QQServer(){
        //注意端口可以放在一个配置文件中，可以改变
        try {
            System.out.println("服务端在9999端口监听");
            ss = new ServerSocket(9999);
            while (true) {//当和某个客户端连接后，会继续监听，因此while
                Socket socket = ss.accept();//如果没有客户端连接就会堵塞在这
                //得到socket关联的对象输入流
                ObjectInputStream ois =
                        new ObjectInputStream(socket.getInputStream());
                //得到socket关联的对象输出流
                ObjectOutputStream oos =
                        new ObjectOutputStream(socket.getOutputStream());
                User user = (User)ois.readObject();//读取客户端发送的User对象
                //创建一个Message对象，准备回复客户端
                Message message = new Message();
                if(checkUser(user.getUserId(),user.getPasswd())){//登陆成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    oos.writeObject(message);
                    //创建一个线程，和客户端保持通信，该线程要有Socket对象
                    ServerConnectClientThread serverConnectClientThread =
                            new ServerConnectClientThread(socket, user.getUserId());
                    //启动该线程
                    serverConnectClientThread.start();
                    //把该线程对象放到一个集合中进行管理
                    ManageServerConnectClientThread.addServerConnectClientThread(user.getUserId(),serverConnectClientThread);
                }else {//登录失败
                    System.out.println("用户Id：" + user.getUserId() + "pwd: "+ user.getPasswd() +"验证失败");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    //登录失败，关闭socket
                    socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //如果服务端退出了while，说明服务器端不再监听了，因此需要关闭资源ServerSocket
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}