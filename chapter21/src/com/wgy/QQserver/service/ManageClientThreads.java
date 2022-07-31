package chapter21.src.com.wgy.QQserver.service;

import java.util.HashMap;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/31 16:28
 */
//没有使用 该类
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();
    //返回 hm
    public static HashMap<String,ServerConnectClientThread> getHm() {
        return hm;
    }
    //添加线程对象到 hm集合
    public static void addClientThread(String userId,ServerConnectClientThread serverConnectClientThread){
        hm.put(userId,serverConnectClientThread);
    }


}
