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

    public static String getCachedToken(Context context){
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getString(KEY_TOKEN, null);
    }

    public void cacheToken(Context context, String token){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        e.putString(KEY_TOKEN, token);
        e.commit();
    }
}
