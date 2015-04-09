package com.example.zhangzhao.secret.atys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.net.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao on 2015/4/9.
 */
public class AtyTimelineMessageListAdapter extends BaseAdapter{

    private Context context = null;
    private List<Message> data = new ArrayList<Message>();


    public AtyTimelineMessageListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Message getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_timeline_list_cell, null);
        }

        TextView tvCellLabel = (TextView)convertView.findViewById(R.id.tvCellLabel);

        return null;
    }

    public Context getContext(){
        return context;
    }

    public void addAll(List<Message> data){
        data.addAll(data);
        notifyDataSetChanged();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }
}
