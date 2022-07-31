package chapter21.src.com.wgy.QQclient.service;

import java.util.HashMap;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/30 14:36
 */
//ManageClientConnectServerThread类
public class ManageClientConnectServerThread {
    //把多个线程放入到要给HashMap集合，key 是用户的id，value就是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    //将某个线程加入到集合
    public static  void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread){
        hm.put(userId,clientConnectServerThread);
    }

    //通过userId得到相应的线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId){
        return hm.get(userId);
    }
}