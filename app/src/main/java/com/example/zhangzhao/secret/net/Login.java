package com.example.zhangzhao.secret.net;

import com.example.zhangzhao.secret.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao on 2015/4/8.
 */
public class Login {
    public Login(String phone_md5, String code, final SuccessCallBack successCallBack, final FailCallBack failCallBack){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch (obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallBack != null){
                                successCallBack.onSuccess(obj.getString(Config.KEY_TOKEN));
                            }
                            break;
                        default:
                            if (failCallBack != null){
                                failCallBack.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(int error) {
                if (failCallBack != null){
                    failCallBack.onFail();
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_LOGIN, Config.KEY_PHONE_MD5, phone_md5, Config.KEY_CODE, code);

    }

    public static interface SuccessCallBack{
        void onSuccess(String token);
    }

    public static interface FailCallBack{
        void onFail();
    }
}
