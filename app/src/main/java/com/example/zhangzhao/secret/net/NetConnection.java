package com.example.zhangzhao.secret.net;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, SuccessCallback successCallback, FailCallback failCallback, String... kvs){

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {

                URLConnection uc;
                try {

                    switch (method){
                        case POST:
                            uc = new URL(url).openConnection();
                            StringBuffer str = new StringBuffer();
                            // str.append()
                            uc.setDoOutput(true);
                            break;
                        default:

                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
    }


    public static interface SuccessCallback{
        void onSeccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
