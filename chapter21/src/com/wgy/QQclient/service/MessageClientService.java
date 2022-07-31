package chapter21.src.com.wgy.QQclient.service;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/31 14:41
 */
//MessageClientService 类

import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 该类提供和消息相关的方法
 */
public class MessageClientService {

    //创建User属性,因为可能在其他地方使用User信息
    // 在User类中创建一个无参构造器

    /**
     *
     * @param senderId  发送者id
     * @param getterId  接收者id
     * @param sendContent  发送内容
     */
    //发送私聊消息
    public void sendPrivateMessage(String senderId ,String getterId,String sendContent){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(sendContent);
        message.setSendTime(new Date().toString());//设置时间
        //获取ObjectOutputStream
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPublicMessage(String senderId ,String sendAllContent){
        Message message = new Message();
        message.setSender(senderId);
        message.setContent(sendAllContent);
        message.setSendTime(new Date().toString());
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        System.out.println(senderId +"对大家说" +sendAllContent);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}