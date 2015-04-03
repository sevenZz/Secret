package com.example.zhangzhao.secret.atys;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import com.example.zhangzhao.secret.R;

/**
 * Created by zhangzhao on 2015/4/3.
 */
public class AtyTimeline extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_timeline);
    }
}
