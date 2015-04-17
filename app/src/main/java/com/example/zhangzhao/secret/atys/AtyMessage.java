package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangzhao.secret.Config;
import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.net.Comment;
import com.example.zhangzhao.secret.net.GetComment;
import com.example.zhangzhao.secret.net.PubComment;
import com.example.zhangzhao.secret.tools.MD5Tool;

import java.util.List;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyMessage extends ListActivity {

    private String phone_md5, msg, msgId, token;
    private TextView tvMsg;
    private AtyTimelineCommentListAdapter adapter;
    private EditText etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_msg);

        etComment = (EditText)findViewById(R.id.etComment);

        Intent data = getIntent();
        phone_md5 = data.getStringExtra(Config.KEY_PHONE_MD5);
        msg = data.getStringExtra(Config.KEY_MSG);
        msgId = data.getStringExtra(Config.KEY_MSG_ID);
        token = data.getStringExtra(Config.KEY_TOKEN);

        tvMsg = (TextView)findViewById(R.id.tvMessage);
        tvMsg.setText(msg);

        adapter = new AtyTimelineCommentListAdapter(this);
        setListAdapter(adapter);

        getComments();


        findViewById(R.id.btnSendComment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etComment.getText())){
                    Toast.makeText(AtyMessage.this, R.string.comment_content_can_not_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                new PubComment(MD5Tool.md5(Config.getCachedPhoneNum(AtyMessage.this)), token, etComment.getText().toString(), msgId, new PubComment.SuccessCallback() {
                    @Override
                    public void onSuccess() {
                        etComment.setText("");

                        getComments();
                    }
                }, new PubComment.FailCallback() {
                    @Override
                    public void onFail(int error) {
                        if (error == Config.RESULT_STATUE_INVALID_TOKEN){
                            startActivity(new Intent(AtyMessage.this, AtyLogin.class));
                            finish();
                        }else {
                            Toast.makeText(AtyMessage.this, R.string.fail_to_pub_comment, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void getComments() {
        final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
        new GetComment(phone_md5, token, msgId, 1, 20, new GetComment.SuccessCallback() {
            @Override
            public void onSuccess(String msgId, int page, int perpage, List<Comment> comments) {
                pd.dismiss();

                adapter.clear();
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
