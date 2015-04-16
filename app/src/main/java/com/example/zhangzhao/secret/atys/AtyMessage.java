package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zhangzhao.secret.Config;
import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.net.Comment;
import com.example.zhangzhao.secret.net.GetComment;

import java.util.List;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyMessage extends ListActivity {

    private String phone_md5, msg, msgId, token;
    private TextView tvMsg;
    private AtyTimelineCommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_msg);

        Intent data = getIntent();
        phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
        msg = data.getStringExtra(Config.KEY_MSG);
        msgId = data.getStringExtra(Config.KEY_MSG_ID);
        token = data.getStringExtra(Config.KEY_TOKEN);

        tvMsg = (TextView)findViewById(R.id.tvMessage);
        tvMsg.setText(msg);

        adapter = new AtyTimelineCommentListAdapter(this);
        setListAdapter(adapter);

        final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
        new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
                pd.dismiss();

                adapter.addAll(comments);
            }
        }, new GetComment.FailCallback() {
            @Override
            public void onFail() {
                pd.dismiss();

                finish();

            }
        });
    }
}
