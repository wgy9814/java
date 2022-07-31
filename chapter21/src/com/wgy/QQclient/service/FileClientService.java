package chapter21.src.com.wgy.QQclient.service;

import chapter21.src.com.wgy.QQclient.qqcommon.Message;
import chapter21.src.com.wgy.QQclient.qqcommon.MessageType;

import java.io.*;
import java.util.Date;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/31 22:18
 */
//FileClientService类
public class FileClientService {
    //发送文件的消息
    public void sendFile(String senderId, String getterId, String fileSrc, String fileDesc){
        Message message = new Message();
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSendTime(new Date().toString());
        message.setMesType(MessageType.MESSAGE_FILE_MES);
        message.setSrc(fileSrc);
        message.setDest(fileDesc);
        String fileContext = "";
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(fileSrc));
            byte[] fileBytes = new byte[(int)new File(fileSrc).length()];
            bis.read(fileBytes);
            message.setFileBytes(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(message.getSender()).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(message.getSender() +" 给 " +message.getGetter() + " 发文件，保存到 " +message.getDest());
    }
}