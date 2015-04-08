package com.example.zhangzhao.secret;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zhangzhao.secret.atys.AtyLogin;
import com.example.zhangzhao.secret.atys.AtyTimeline;
import com.example.zhangzhao.secret.ld.MyContacts;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = Config.getCachedToken(this);
        if (token != null){
            Intent i = new Intent(this, AtyTimeline.class);
            i.putExtra(Config.KEY_TOKEN, token);
            startActivity(i);
        } else{
            startActivity(new Intent(this, AtyLogin.class));
        }

        finish();
    }



}
