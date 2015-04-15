package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zhangzhao.secret.Config;
import com.example.zhangzhao.secret.R;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyMessage extends Activity {

    private String phone_md5, msg, msgId, token;
    private TextView tvMsg;

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
    }
}
