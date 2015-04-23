package com.example.zhangzhao.secret.net;

import com.example.zhangzhao.secret.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangzhao on 2015/4/7.
 */
public class GetCode {

    public GetCode(String phone, final SuccessCallBack successCallBack, final FailCallBack failCallBack){

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallBack != null) {
                                successCallBack.onSuccess();
                            }
                            break;
                        default:
                            if (failCallBack != null) {
                                failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                    if (failCallBack != null){
                        failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(int error) {
                failCallBack.onFail(Config.RESULT_STATUE_FAIL);
            }
        }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, phone);


    }

    public static interface SuccessCallBack{
        void onSuccess();
    }

    public static interface FailCallBack{
        void onFail(int error);
    }
}
