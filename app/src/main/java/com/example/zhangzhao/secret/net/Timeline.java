package com.example.zhangzhao.secret.net;

import com.example.zhangzhao.secret.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao on 2015/4/9.
 */
public class Timeline {
    public Timeline(String phone_md5, String token, int page, int perpage, final SuccessCallBack successCallBack, final FailCallBack failCallBack){
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);

                    switch (obj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallBack != null){
                                List<Message> msgs = new ArrayList<Message>();
                                JSONArray msgJsonArray = obj.getJSONArray(Config.KEY_TIMELINE);
                                JSONObject msgObj;

                                for (int i = 0; i < msgJsonArray.length(); i++){
                                    msgObj = msgJsonArray.getJSONObject(i);
                                    msgs.add(new Message(msgObj.getString(Config.KEY_MSG_ID),
                                            msgObj.getString(Config.KEY_MSG),
                                            msgObj.getString(Config.KEY_PHONE_MD5)));
                                }

                                successCallBack.onSuccess(obj.getInt(Config.KEY_PAGE), obj.getInt(Config.KEY_PERPAGE), msgs);
                            }
                            break;
                        case Config.RESULT_STATUE_INVALID_TOKEN:
                            if(failCallBack != null){
                                failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                            }
                            break;
                        default:
                            if (failCallBack != null){
                                failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                            }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallBack != null){
                    failCallBack.onFail(Config.RESULT_STATUE_FAIL);
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_TIMELINE,
                Config.KEY_PHONE_MD5, phone_md5,
                Config.KEY_TOKEN, token,
                Config.KEY_PAGE, page + " ",
                Config.KEY_PERPAGE, perpage + " ");
    }


    public static interface SuccessCallBack{
        void onSuccess(int page, int perpage, List<Message> timeline);
    }
    public static interface FailCallBack{
        void onFail(int error);
    }
}
