package com.example.zhangzhao.secret.net;

import com.example.zhangzhao.secret.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao on 2015/4/8.
 */
public class UploadContacts {
    public UploadContacts(String phone_md5, String token, String contacts, final SuccessCallBack successCallBack, final FailCallBack failCallBack){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch (obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallBack != null){
                                successCallBack.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUE_INVALID_TOKEN:
                            if (failCallBack != null){
                                failCallBack.onFail(Config.RESULT_STATUE_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallBack != null){
                                failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(int error) {
                if (failCallBack != null){
                    failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                }
            }
        },Config.KEY_ACTION, Config.ACTION_UPLOAD_CONTACTS, Config.KEY_PHONE_MD5, phone_md5, Config.KEY_TOKEN, token, Config.KEY_CONTACTS, contacts);

    }

    public static interface SuccessCallBack{
        void onSuccess();
    }
    public static interface FailCallBack{
        void onFail(int error);
    }
}
