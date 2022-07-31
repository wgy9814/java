package chapter21.src.com.wgy.QQserver.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 15:02
 */
//ManageServerConnectClientThread类

import java.util.HashMap;
import java.util.Iterator;
/**
 * 该类用于管理和客户端通信的线程
 */
public class ManageServerConnectClientThread {
    //把多个线程放入到要给HashMap集合，key 是用户的id，value就是线程
    private static HashMap<String,ServerConnectClientThread> hm = new HashMap<>();

    //将某个线程加入到集合
    public static void addServerConnectClientThread(String userId, ServerConnectClientThread serverConnectClientThread){
        hm.put(userId,serverConnectClientThread);
    }
    //通过userId得到相应的线程
    public static ServerConnectClientThread getServerConnectClientThread(String userId){
        return hm.get(userId);
    }
    //返回 hm
    public static HashMap<String,ServerConnectClientThread> getHm() {
        return hm;
    }
    //编写方法，可以返回在线用户列表
    public static String getOnlineUser() {
        //集合遍历，遍历 hashMap的key
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()){
            onlineUserList += iterator.next() + " ";
        }
        return onlineUserList;
    }

    //ManageServerConnectClientThread类
    //将某个线程从集合移除
    public static void removeServerConnectClientThread(String userId){
        hm.remove(userId);
    }
}