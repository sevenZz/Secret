package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangzhao.secret.Config;
import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.ld.MyContacts;
import com.example.zhangzhao.secret.net.Message;
import com.example.zhangzhao.secret.net.Timeline;
import com.example.zhangzhao.secret.net.UploadContacts;
import com.example.zhangzhao.secret.tools.MD5Tool;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyTimeline extends ListActivity {


    private String phone_num, token, phone_md5;
    private AtyTimelineMessageListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);

        adapter = new AtyTimelineMessageListAdapter(this);
        setListAdapter(adapter);

        phone_num = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = MD5Tool.md5(phone_num);

        final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

        new UploadContacts(phone_md5, token, MyContacts.getContactsJSONString(this), new UploadContacts.SuccessCallBack() {
            @Override
            public void onSuccess() {
                loadMessage();

                pd.dismiss();
            }
        }, new UploadContacts.FailCallBack() {
            @Override
            public void onFail(int error) {
                pd.dismiss();
                if (error == Config.RESULT_STATUE_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
                    finish();
                }else{
                    loadMessage();
                }
            }
        });
    }

    private void loadMessage(){

        final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));

        new Timeline(phone_md5, token, 1, 20, new Timeline.SuccessCallBack() {
            @Override
            public void onSuccess(int page, int perpage, List<Message> timeline) {
                pd.dismiss();

                adapter.addAll(timeline);
                //setListAdapter(adapter);
            }
        }, new Timeline.FailCallBack() {
            @Override
            public void onFail(int error) {
                pd.dismiss();

                if (error == Config.RESULT_STATUE_INVALID_TOKEN){
                    startActivity(new Intent(AtyTimeline.this, AtyLogin.class));
                    finish();
                }else{
                    Toast.makeText(AtyTimeline.this, R.string.fail_to_load_timeline_data, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Message msg = adapter.getItem(position);

        Intent i = new Intent(this, AtyMessage.class);
        i.putExtra(Config.KEY_MSG, msg.getMsg());
        i.putExtra(Config.KEY_MSG_ID, msg.getMsgId());
        i.putExtra(Config.KEY_PHONE_MD5, msg.getPhone_md5());
        i.putExtra(Config.KEY_TOKEN, token);

        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_aty_timeline, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuShowAtyPubMessage:
                startActivity(new Intent(AtyTimeline.this, AtyPubMessage.class));
                break;
            default:
                break;
        }
        return true;
    }
}
