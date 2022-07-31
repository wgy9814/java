package chapter21.src.com.wgy.QQserver.qqcommon;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/24 13:43
 */
//Message类

import java.io.Serializable;

/**
 * 表示客户端和服务器端通讯时的消息对象
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;//增强兼容性
    private String sender;//发送方
    private String getter;//接收方
    private String content;//发送内容
    private String sendTime;//发生时间
    private String mesType;//消息类型【在接口定义已知类型】

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}