package com.example.zhangzhao.secret.atys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhangzhao.secret.R;
import com.example.zhangzhao.secret.net.Comment;
import com.example.zhangzhao.secret.net.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhao on 2015/4/9.
 */
public class AtyTimelineCommentListAdapter extends BaseAdapter{

    private Context context = null;
    private List<Comment> data = new ArrayList<Comment>();


    public AtyTimelineCommentListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Comment getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.aty_comments_list_cell, null);
            convertView.setTag(new ListCell((TextView)convertView.findViewById(R.id.tvCellLabel)));
        }

        ListCell lc = (ListCell)convertView.getTag();

        Comment comment = getItem(position);

        lc.getTvCellLabel().setText(comment.getContent());


        return convertView;
    }

    public Context getContext(){
        return context;
    }

    public void addAll(List<Comment> comments){
        this.data.addAll(comments);
        notifyDataSetChanged();
    }

    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }



    private static class ListCell{
        private TextView tvCellLabel;

        public ListCell(TextView tvCellLabel){
            this.tvCellLabel = tvCellLabel;
        }

        public TextView getTvCellLabel() {
            return tvCellLabel;
        }
    }
}
