package com.example.zhangzhao.secret.net;

/**
 * Created by zhangzhao on 2015/4/15.
 */
public class Comment {

    private String content, phone_md5;

    public  Comment(String content, String phone_md5){
        this.content = content;
        this.phone_md5 = phone_md5;
    }

    public String getContent(){
        return content;
    }

    public String getPhone_md5() {
        return phone_md5;
    }
}
