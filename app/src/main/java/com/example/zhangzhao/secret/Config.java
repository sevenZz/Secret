package com.example.zhangzhao.secret;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class Config {

    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.example.zhangzhao.secret";
    public static final String CHARSET = "UTF-8";

    //public static final String SERVER_URL = "http://demo.eoeschool.com/api/v1/nimings/io";
    public static final String SERVER_URL = "http://10.202.101.231:81/Output.ashx";

    public static final String ACTION_GET_CODE = "send_pass";
    public static final String KEY_ACTION = "action";
    public static final String KEY_PHONE_NUM = "phone";
    public static final String KEY_STATUS = "status";

    public static final int RESULT_STATUS_SUCCESS = 1;
    public static final int RESULT_STATUE_FAIL = 0;
    public static final int RESULT_STATUE_INVALID_TOKEN = 2;
    public static final String ACTION_LOGIN = "login";
    public static final String KEY_PHONE_MD5 = "phone_md5";
    public static final String KEY_CODE = "code";

    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public static void cacheToken(Context context, String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }

    public static String getCachedPhoneNum(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_PHONE_NUM, null);
    }

    public static void cachePhoneNum(Context context, String phone){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_PHONE_NUM, phone);
        e.commit();
    }
}
