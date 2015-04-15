package com.example.zhangzhao.secret.net;

import com.example.zhangzhao.secret.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao on 2015/4/15.
 */
public class GetComment {

    public GetComment(String phone_md5, String token, String msgId, int page, int perpage, final SuccessCallback successCallback, final FailCallback failCallback){
        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    switch (jsonObject.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback != null) {

                                List<Comment> comments = new ArrayList<Comment>();
                                JSONArray commentJsonArray = jsonObject.getJSONArray(Config.KEY_COMMENTS);
                                JSONObject commentObj;

                                for (int i = 0; i < commentJsonArray.length(); i++) {
                                    commentObj = commentJsonArray.getJSONObject(i);
                                    comments.add(new Comment(commentObj.getString(Config.KEY_CONTENT), commentObj.getString(Config.KEY_PHONE_MD5)));
                                }

                                successCallback.onSuccess(jsonObject.getString(Config.KEY_MSG_ID), jsonObject.getInt(Config.KEY_PAGE), jsonObject.getInt(Config.KEY_PERPAGE), comments);
                            }

                            break;
                        case Config.RESULT_STATUE_INVALID_TOKEN:

                            break;
                        default:
                            if (failCallback != null){
                                failCallback.onFail();
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
                if (failCallback != null){
                    failCallback.onFail();
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_GET_COMMENT,
                Config.KEY_TOKEN, token,
                Config.KEY_MSG_ID, msgId,
                Config.KEY_PAGE, page + "",
                Config.KEY_PERPAGE, perpage + "");
    }

    public static interface SuccessCallback{
        void onSuccess(String msgId, int page, int perpage, List<Comment> comments);
    }

    public static interface FailCallback{
        void onFail();
    }
}
