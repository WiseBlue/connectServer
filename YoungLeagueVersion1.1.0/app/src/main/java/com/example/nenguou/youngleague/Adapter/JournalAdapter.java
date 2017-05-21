package com.example.nenguou.youngleague.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nenguou.youngleague.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19.
 */

public class JournalAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Map<String, Object>> items;
    private String TAG="JournalAdapter";

    public JournalAdapter(Context context,List<Map<String, Object>> items){
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
            convertView=inflater.inflate(R.layout.content_journal,null);
            viewHolder.content= (TextView) convertView.findViewById(R.id.content_joural_content);
            viewHolder.title= (TextView) convertView.findViewById(R.id.content_joural_title);
            viewHolder.date= (TextView) convertView.findViewById(R.id.content_joural_date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Map<String,Object> content=items.get(position);
        viewHolder.title.setText(content.get("title").toString());
        viewHolder.content.setText(content.get("content").toString());
        viewHolder.date.setText(stringToDate(content.get("date").toString()));
        Log.i(TAG,content.get("date").toString());

        return convertView;
    }
    class ViewHolder{
        TextView date;
        TextView title;
        TextView content;
    }
    public static String stringToDate(String lo){

        long time = Long.parseLong(lo);

        Date date = new Date(time);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return sd.format(date);

    }
}
