package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */

public class AnnounceAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> items;
    private String TAG="AnnounceAdapter";

    public AnnounceAdapter(Context context,List<Map<String, Object>> items){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if (convertView==null){
            convertView=inflater.inflate(R.layout.content_annocement,null);
            viewHolder.title= (TextView) convertView.findViewById(R.id.announce_tv_title);
            viewHolder.author= (TextView) convertView.findViewById(R.id.announce_tv_author);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Map<String,Object> content=items.get(position);
        viewHolder.title.setText(content.get("title").toString());
        viewHolder.author.setText(content.get("author").toString());
        Log.i(TAG,content.get("title").toString());

        return convertView;
    }
    class ViewHolder{
        TextView author;
        TextView title;
    }


}
