package com.example.zhangzhao.secret.net;

/**
 * Created by zhangzhao on 2015/4/9.
 */
public class Message {
    private String msgId = null, msg = null, phone_md5 = null;

    public Message(String msgId, String msg, String phone_md5){
        this.msgId = msgId;
        this.msg = msg;
        this.phone_md5 = phone_md5;
    }

    public String getMsgId(){
        return msgId;
    }

    public String getMsg() {
        return msg;
    }

    public String getPhone_md5() {
        return phone_md5;
    }
}
