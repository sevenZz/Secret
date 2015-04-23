package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhangzhao.secret.Config;
import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.net.Publish;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyPubMessage extends Activity {

    private EditText etMsgContent;
    private String phone_md5, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_pub_message);

        etMsgContent = (EditText)findViewById(R.id.etMsgContent);

        Intent i = getIntent();
        phone_md5 = i.getStringExtra(Config.KEY_PHONE_MD5);
        token = i.getStringExtra(Config.KEY_TOKEN);

        findViewById(R.id.btnPublish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgContent = etMsgContent.getText().toString();

                if(TextUtils.isEmpty(msgContent)){
                    Toast.makeText(AtyPubMessage.this, R.string.message_can_not_be_empty, Toast.LENGTH_LONG).show();
                }

                new Publish(phone_md5, token, msgContent, new Publish.SuccessCallback() {
                    @Override
                    public void onSuccess() {

                        Toast.makeText(AtyPubMessage.this, R.string.success_to_pub_message, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Publish.FailCallback() {
                    @Override
                    public void onFail(int error) {
                        if (error == Config.RESULT_STATUE_INVALID_TOKEN){
                            startActivity(new Intent(AtyPubMessage.this, AtyLogin.class));
                            finish();
                        }else{
                            Toast.makeText(AtyPubMessage.this, R.string.fail_to_pub_message, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
